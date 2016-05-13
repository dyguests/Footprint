package com.fanhl.footprint.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * 定时记录 当前位置/速度/方向
 */
class FootprintService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }
}
