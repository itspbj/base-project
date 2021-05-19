package com.lyft.android.photobrowser.toolbar

import com.lyft.android.photobrowser.R
import com.lyft.android.photobrowser.views.Plugin


class ToolbarPlugin : Plugin() {
    override fun layoutId(): Int {
        return R.layout.toolbar_view
    }
}
