package com.fanhl.footprint

import android.app.Application
import com.fanhl.footprint.io.FootprintClient

/**
 * Created by fanhl on 16/5/12.
 */
class App : Application() {
    val client by lazy { FootprintClient(this) }

    override fun onCreate() {
        super.onCreate()
    }
}

