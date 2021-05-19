package com.lyft.android.photobrowser.photos

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyft.android.photobrowser.R
import com.lyft.android.photobrowser.views.ImageLoader
import com.lyft.android.photobrowser.views.Plugin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class PhotosPlugin(
    private val photosApiService: PhotoApiService
) : Plugin() {

    private val recyclerView: RecyclerView by findView(R.id.recycler_view)

    override fun onAttach(parent: ViewGroup) {
        super.onAttach(parent)
        recyclerView.layoutManager = LinearLayoutManager(getView().context)

        executeRequest()
    }

    override fun layoutId(): Int {
        return R.layout.photos_view
    }

    private fun executeRequest() {
        photosApiService
            .getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                result.onSuccess { response ->
                    val adapter =
                        PhotosAdapter(ImageLoader.instance, getView().context, response.value.hits)
                    recyclerView.adapter = adapter
                }
            }
            .addTo(compositeDisposable)
    }
}
