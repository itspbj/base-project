package com.lyft.android.photobrowser.views

import kotlin.reflect.KProperty


class CachedView<T>(
    private val objectProvider: () -> T
) {

    private var cachedObject: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return cachedObject ?: objectProvider().also { cachedObject = it }
    }

    fun reset() {
        cachedObject = null
    }
}
