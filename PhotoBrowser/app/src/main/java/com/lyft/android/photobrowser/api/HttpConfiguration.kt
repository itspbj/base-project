package com.lyft.android.photobrowser.api


class HttpConfiguration {

    companion object {
        var instance = HttpConfiguration()
    }

    fun baseUrl() = "pixabay.com"

    fun baseApi() = "api"

    fun apiKey() = "8630898-e092bf16cb1dd9ff6a483dabf"
}
