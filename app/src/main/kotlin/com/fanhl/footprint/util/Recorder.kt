package com.fanhl.footprint.util

import android.location.Location
import android.util.Log
import com.fanhl.footprint.constant.Constant
import com.fanhl.footprint.model.Foot
import java.util.*

/**
 * 足迹记录器
 */
class Recorder {
    companion object {
        val TAG = Recorder::class.java.simpleName
    }

    val list = ArrayList<Foot>()//need to change LinkedList to use list.last ?

    fun add(location: Location?, orientation: FloatArray) {
        val id = list.size
        val time = System.currentTimeMillis()
        val velocity = computeVelocity(location)
        val angular = Math.toRadians(orientation[0].toDouble()).toFloat()
        val acceleration = computeAcceleration(velocity)
        val angularVelocity = computeAngularVelocity(angular)
        val centrifugal = computeCentrifugal(angularVelocity, velocity)

        list += Foot(id, time, location, velocity, angular, acceleration, angularVelocity, centrifugal)
    }

    fun save() {
        Log.d(TAG, "save")
        Log.d(TAG, list.toString())
        FileManager.save(list)
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
        return (angular - lastAngular) / Constant.INTERVAL_SECONDS
    }

    /**|w.v|离心力*/
    private fun computeCentrifugal(angularVelocity: Float, velocity: Float): Float {
        return Math.abs(angularVelocity * velocity)
    }
}