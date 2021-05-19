package com.lyft.android.photobrowser.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyft.android.photobrowser.views.PluginManager


abstract class BaseActivity : AppCompatActivity() {

    val pluginManager = PluginManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController.instance.onAttach(this)
    }

    override fun onResume() {
        super.onResume()
        ActivityController.instance.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        pluginManager.detachPlugins()
        ActivityController.instance.onDetach()
    }
}
