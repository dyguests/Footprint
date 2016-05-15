package com.fanhl.footprint.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

/**
 * 定时记录 当前位置/速度/方向
 */
class FootprintService : Service() {
    companion object {
        private val START_ACTION = "FootprintService.START_ACTION"

        /**
         * Start the service if not and prepare music for playing with given resource id.If the
         * service is already started,the music will be update with given resource id.

         * @param musicRawResId music resource id.
         */
        fun start(context: Context) {
            val intent = Intent(context, FootprintService::class.java)
            intent.action = START_ACTION
            context.startService(intent)
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, FootprintService::class.java))
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}
