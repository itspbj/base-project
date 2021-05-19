package com.lyft.android.photobrowser.photos

import com.google.gson.annotations.SerializedName


data class Photo(
    @SerializedName("id")
    val id: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("likes")
    val likes: Int,
)
