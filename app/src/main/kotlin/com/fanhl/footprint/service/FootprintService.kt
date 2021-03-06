package com.fanhl.footprint.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.fanhl.footprint.constant.Constant
import com.fanhl.footprint.util.Recorder
import com.fanhl.footprint.util.sensor.LocationSensor
import com.fanhl.footprint.util.sensor.OrientationSensor
import java.util.*

/**
 * 定时记录 当前位置/速度/方向
 */
class FootprintService : Service() {
    companion object {
        val TAG = FootprintService::class.java.simpleName
        private val START_ACTION = "FootprintService.START_ACTION"

        var isActive = false

        /**
         * Start the service if not and prepare music for playing with given resource id.If the
         * service is already started,the music will be update with given resource id.

         * @param musicRawResId music resource id.
         */
        fun start(context: Context) {
            val intent = Intent(context, FootprintService::class.java)
            intent.action = START_ACTION
            context.startService(intent)

            isActive = true
        }

        fun stop(context: Context) {
            val intent = Intent(context, FootprintService::class.java)
            context.stopService(intent)

            isActive = false
        }
    }

    lateinit var orientationSensor: OrientationSensor
    lateinit var locationSensor: LocationSensor
    /**计时器*/
    var timer: Timer? = null
    /**足迹记录器*/
    var recorder: Recorder? = null

    override fun onCreate() {
        super.onCreate()
        locationSensor = LocationSensor(this)
        orientationSensor = OrientationSensor(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        timer?.cancel()

        locationSensor.disable()
        orientationSensor.disable()

        saveData()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val result = super.onStartCommand(intent, flags, startId)

        initData()
        startTimer()

        return result
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    private fun initData() {
        recorder = Recorder()

        locationSensor.enable()
        orientationSensor.enable()
    }

    private fun saveData() {
        recorder?.save(this)
    }

    private fun startTimer() {
        if (timer == null) {
            timer = Timer("Footprint recorder", true)
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    recordFoot()
                }
            }, 0L, Constant.LOCATION_INTERVAL)
        }
    }

    /**
     * 记录当前一步足迹(每秒)
     */
    private fun recordFoot() {
        val (currentLocation, lastLocation) = locationSensor.getLocation()
        val orientation = orientationSensor.getOrientation()

        recorder?.add(currentLocation, orientation)
    }
}

