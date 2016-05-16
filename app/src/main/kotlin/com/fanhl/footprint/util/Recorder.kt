package com.fanhl.footprint.util

import android.location.Location
import com.fanhl.footprint.constant.Constant
import com.fanhl.footprint.model.Foot
import java.util.*

/**
 * 足迹记录器
 */
class Recorder {
    val list = ArrayList<Foot>()

    fun add(location: Location?, orientation: FloatArray) {
        val id = list.size
        val time = System.currentTimeMillis()
        val velocity = computeVelocity(location)
        val angular = orientation[0]
        val acceleration = computeAcceleration(velocity)
        val angularVelocity = computeAngularVelocity(angular)
        val centrifugal = computeCentrifugal(angularVelocity, velocity)

        list += Foot(id, time, location, velocity, angular, acceleration, angularVelocity, centrifugal)
    }

    fun save() {

    }

    /**计算速度*/
    private fun computeVelocity(location: Location?): Float {
        if (location == null) return 0f;
        if (list.isEmpty()) return 0f;
        val lastLocation = list[0].location ?: return 0f
        return location.distanceTo(lastLocation) / Constant.INTERVAL_SECONDS
    }

    private fun computeAcceleration(velocity: Float): Float {
        if (list.isEmpty()) return 0f;
        val lastVelocity = list[-1].velocity
        return (velocity - lastVelocity) / Constant.INTERVAL_SECONDS
    }

    private fun computeAngularVelocity(angular: Float): Float {
        if (list.isEmpty()) return 0f;
        val lastAngular = list[-1].angular
        return (Math.toRadians(((angular - lastAngular).toDouble())) / Constant.INTERVAL_SECONDS).toFloat()
    }

    /**
     * |w.v|离心力
     */
    private fun computeCentrifugal(angularVelocity: Float, velocity: Float): Float {
        return Math.abs(angularVelocity * velocity)
    }

}