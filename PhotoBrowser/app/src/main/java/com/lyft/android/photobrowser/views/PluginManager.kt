package com.lyft.android.photobrowser.views

import android.view.ViewGroup


class PluginManager {
    private val plugins = mutableListOf<Plugin>()

    fun attachPlugin(viewGroup: ViewGroup, plugin: Plugin) {
        plugin.onAttach(viewGroup)
        plugins.add(plugin)
    }

    fun detachPlugins() {
        for (plugin in plugins) {
            plugin.onDetach()
        }
        plugins.clear()
    }
}
