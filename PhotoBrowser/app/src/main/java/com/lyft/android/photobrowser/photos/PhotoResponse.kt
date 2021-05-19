package com.lyft.android.photobrowser.photos

import com.google.gson.annotations.SerializedName


data class PhotoResponse(
    @SerializedName("hits")
    val hits: List<Photo>,
)
