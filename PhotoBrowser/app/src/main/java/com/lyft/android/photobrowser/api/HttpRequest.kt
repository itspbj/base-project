package com.lyft.android.photobrowser.api


data class HttpRequest<T>(
    val url: String,
    val queryParams: List<QueryParam>,
    val responseClass: Class<T>
)
