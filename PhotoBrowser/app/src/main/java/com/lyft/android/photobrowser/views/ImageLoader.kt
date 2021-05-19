package com.lyft.android.photobrowser.views

import android.widget.ImageView
import com.lyft.android.photobrowser.activity.ActivityController
import com.squareup.picasso.Picasso


class ImageLoader(
    private val picasso: Picasso,
    private val activityController: ActivityController
) {

    companion object {
        val instance = ImageLoader(Picasso.get(), ActivityController.instance)
    }

    fun load(url: String, imageView: ImageView) {
        val width = activityController.activity?.windowManager?.defaultDisplay?.width
        if (width != null) {
            picasso.load(url)
                .centerCrop()
                .resize(width, width)
                .into(imageView)
        }
    }
}
