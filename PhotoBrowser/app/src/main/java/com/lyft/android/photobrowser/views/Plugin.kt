package com.lyft.android.photobrowser.views

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import io.reactivex.disposables.CompositeDisposable


abstract class Plugin {
    var view: View? = null
    val compositeDisposable = CompositeDisposable()

    private val cachedViews = mutableListOf<CachedView<out View>>()

    @CallSuper
    open fun onAttach(parent: ViewGroup) {
        view = LayoutInflater.from(parent.context).inflate(layoutId(), parent)
    }

    @CallSuper
    open fun onDetach() {
        compositeDisposable.dispose()
        for (cachedView in cachedViews) {
            cachedView.reset()
        }
        cachedViews.clear()
        view = null
    }

    abstract fun layoutId(): Int

    internal fun getView(): View {
        return view!!
    }

    internal fun getContext(): Context {
        return view!!.context
    }

    internal fun getResources(): Resources {
        return view!!.resources
    }

    protected fun <T : View> findView(@IdRes viewId: Int): CachedView<T> {
        val cachedView = CachedView<T> {
            getView().findViewById(viewId)
        }
        cachedViews.add(cachedView)
        return cachedView
    }
}
