package com.lyft.android.photobrowser.photos

import com.lyft.android.photobrowser.api.HttpRequest
import com.lyft.android.photobrowser.api.HttpResponse
import com.lyft.android.photobrowser.api.HttpService
import io.reactivex.Single

class PhotoApiService(
    private val httpService: HttpService
) {
    companion object {
        var instance = PhotoApiService(HttpService.instance)
    }

    fun getPhotos(): Single<Result<HttpResponse<PhotoResponse>>> {
        return Single
            .fromCallable {
                HttpRequest("", emptyList(), PhotoResponse::class.java)
            }.flatMap {
                httpService.execute(it)
            }
    }
}
