package com.lyft.android.photobrowser.activity

import android.app.Activity


class ActivityController {
    var activity: Activity? = null

    companion object {
        val instance = ActivityController()
    }

    fun onAttach(activity: Activity) {
        this.activity = activity
    }

    fun onDetach() {
        this.activity = null
    }
}
