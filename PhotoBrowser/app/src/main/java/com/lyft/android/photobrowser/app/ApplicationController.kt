package com.lyft.android.photobrowser.app

import android.app.Application


class ApplicationController {

    companion object {
        val instance = ApplicationController()
    }

    private var _application: Application? = null

    fun onAttach(application: Application) {
        this._application = application
    }

    fun onDetach(application: Application) {
        if (application == this._application) {
            this._application = null
        }
    }

    fun getApplication(): Application {
        return _application!!
    }
}
