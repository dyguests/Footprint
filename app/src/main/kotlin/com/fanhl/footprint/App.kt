package com.fanhl.footprint

import android.app.Application
import android.content.Context
import com.fanhl.footprint.api.local.LocalClient

/**
 * Created by fanhl on 16/5/12.
 */
class App : Application() {
    val localClient by lazy { LocalClient(this) }

    override fun onCreate() {
        super.onCreate()
    }
}

