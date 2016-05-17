package com.fanhl.footprint.util

import android.content.Context
import android.location.Location
import android.util.Log
import com.fanhl.footprint.constant.Constant
import com.fanhl.footprint.core.AnalysisUtil
import com.fanhl.footprint.model.Foot
import com.fanhl.footprint.service.FootprintService
import org.greenrobot.eventbus.EventBus
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
        val isSharpAcceleration = AnalysisUtil.isSharpAcceleration(acceleration)
        val isSharpDeceleration = AnalysisUtil.isSharpDeceleration(acceleration)
        val isSharpTurn = AnalysisUtil.isSharpTurn(centrifugal)

        val foot = Foot(id, time, location, velocity, angular, acceleration, angularVelocity, centrifugal)

        Log.d(FootprintService.TAG, "${foot.toStringCn()}")
        EventBus.getDefault().post(foot)

        list += foot
    }

    fun save(context: Context) {
        Log.d(TAG, "save()")
        FileManager.save(context, list)
    }

    /**计算速度*/
    private fun computeVelocity(location: Location?): Float {
        return location?.speed ?: 0f
//        if (location == null) return 0f;
//        if (list.isEmpty()) return 0f;
//        val lastLocation = list[0].location ?: return 0f
//        Log.d(TAG, "location.distanceTo(lastLocation):${location.distanceTo(lastLocation)}")
//        return location.distanceTo(lastLocation) / Constant.INTERVAL_SECONDS
    }

    private fun computeAcceleration(velocity: Float): Float {
        if (list.isEmpty()) return 0f;
        val lastVelocity = list[list.size - 1].velocity
        return (velocity - lastVelocity) / Constant.INTERVAL_SECONDS
    }

    private fun computeAngularVelocity(angular: Float): Float {
        if (list.isEmpty()) return 0f;
        val lastAngular = list[list.size - 1].angular
        return (angular - lastAngular) / Constant.INTERVAL_SECONDS
    }

    /**|w.v|离心力*/
    private fun computeCentrifugal(angularVelocity: Float, velocity: Float): Float {
        return Math.abs(angularVelocity * velocity)
    }
}