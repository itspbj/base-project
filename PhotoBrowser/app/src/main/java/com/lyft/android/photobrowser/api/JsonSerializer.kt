package com.lyft.android.photobrowser.api

import com.google.gson.Gson

class JsonSerializer(
    val gson: Gson
) {
    companion object {
        var instance = JsonSerializer(Gson())
    }

    fun toJson(value: String): String? {
        return gson.toJson(value)
    }

    fun <T> fromJson(value: String, valueClass: Class<T>): T {
        return gson.fromJson(value, valueClass)
    }
}
