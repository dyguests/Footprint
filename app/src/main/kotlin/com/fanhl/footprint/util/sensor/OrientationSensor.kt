package com.fanhl.footprint.util.sensor

import android.hardware.SensorManager

/**
 * 方向传感器
 * Created by fanhl on 16/5/13.
 */
object OrientationSensor {
    private var accelerometerValues = FloatArray(3)
    private var magneticFieldValues = FloatArray(3)

    fun calculateOrientation(): FloatArray {
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