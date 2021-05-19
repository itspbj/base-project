package com.lyft.android.photobrowser

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.lyft.android.photobrowser.activity.ActivityController
import com.lyft.android.photobrowser.photos.PhotoApiService
import com.lyft.android.photobrowser.photos.PhotosPlugin
import com.lyft.android.photobrowser.toolbar.ToolbarPlugin
import com.lyft.android.photobrowser.views.PluginManager

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarContainer: LinearLayout
    private lateinit var photosContainer: LinearLayout
    private val pluginManager = PluginManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController.instance.onAttach(this)
        setContentView(R.layout.activity_main)

        toolbarContainer = findViewById(R.id.toolbar_container)
        photosContainer = findViewById(R.id.photos_container)

        attachToolbarPlugin()
        attachPhotosPlugin()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityController.instance.onDetach()
        pluginManager.detachPlugins()
    }

    private fun attachToolbarPlugin() {
        val toolbarPlugin = ToolbarPlugin()
        pluginManager.attachPlugin(toolbarContainer, toolbarPlugin)
    }

    private fun attachPhotosPlugin() {
        val photosPlugin = PhotosPlugin(PhotoApiService.instance)
        pluginManager.attachPlugin(photosContainer, photosPlugin)
    }
}
