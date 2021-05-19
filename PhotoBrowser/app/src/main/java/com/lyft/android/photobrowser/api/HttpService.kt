package com.lyft.android.photobrowser.api

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpService(
    private val client: OkHttpClient,
    private val jsonSerializer: JsonSerializer,
    private val httpConfiguration: HttpConfiguration
) {
    companion object {
        var instance =
            HttpService(
                OkHttpClient.Builder()
                    .build(),
                JsonSerializer.instance,
                HttpConfiguration.instance
            )

        const val HEADER_KEY = "key"
    }

    fun <T> execute(request: HttpRequest<T>): Single<Result<HttpResponse<T>>> {
        return Single.fromCallable {
            val url = createUrl(request)
            Log.d("URL", "url:$url")
            val httpRequest = Request.Builder()
                .url(url)
                .build()
            val response = client.newCall(httpRequest).execute()
            try {
                val body = response.body()?.string()
                if (body != null) {
                    val json = jsonSerializer.fromJson(body, request.responseClass)
                    if (json != null) {
                        Log.d("json", "json:$json")
                        return@fromCallable Result.success(HttpResponse(json))
                    } else {
                        return@fromCallable Result.failure<HttpResponse<T>>(NullPointerException())
                    }
                } else {
                    return@fromCallable Result.failure<HttpResponse<T>>(NullPointerException())
                }
            } catch (exception: IOException) {
                return@fromCallable Result.failure<HttpResponse<T>>(exception)
            }
        }
    }

    private fun <T> createUrl(request: HttpRequest<T>): HttpUrl {
        val urlBuilder = HttpUrl.Builder()
            .scheme("https")
            .host(httpConfiguration.baseUrl())
            .addPathSegment(httpConfiguration.baseApi())
            .addQueryParameter(HEADER_KEY, httpConfiguration.apiKey())
        if (request.url.isNotEmpty()) {
            urlBuilder.addPathSegment(request.url)
        }
        return urlBuilder.build()
    }
}

