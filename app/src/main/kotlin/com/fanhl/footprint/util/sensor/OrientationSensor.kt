package com.fanhl.footprint.util.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * 方向传感器
 * Created by fanhl on 16/5/13.
 */
class OrientationSensor(context: Context) {
    private var sm: SensorManager
    /**加速度传感器*/
    private var aSensor: Sensor
    /**磁场传感器*/
    private var mSensor: Sensor

    private var accelerometerValues = FloatArray(3)
    private var magneticFieldValues = FloatArray(3)

    /**传感器回调*/
    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            when (sensorEvent.sensor.type) {
                Sensor.TYPE_ACCELEROMETER  -> accelerometerValues = sensorEvent.values
                Sensor.TYPE_MAGNETIC_FIELD -> magneticFieldValues = sensorEvent.values
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
    }

    init {
        sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        aSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    fun enable() {
        sm.registerListener(sensorEventListener, aSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sm.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun disable() {
        sm.unregisterListener(sensorEventListener)
    }

    fun getOrientation(): FloatArray {
        val R = FloatArray(9)
        val values = FloatArray(3)
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues)
        SensorManager.getOrientation(R, values)

        // 要经过一次数据格式的转换，转换为度
        values[0] = Math.toDegrees(values[0].toDouble()).toFloat()
        values[1] = Math.toDegrees(values[1].toDouble()).toFloat()
        values[2] = Math.toDegrees(values[2].toDouble()).toFloat()

        return values
    }
}