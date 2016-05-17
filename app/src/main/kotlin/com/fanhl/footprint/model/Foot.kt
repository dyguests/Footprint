package com.fanhl.footprint.model

import android.location.Location
import com.fanhl.footprint.util.DateUtil

data class Foot(
        val id: Int,
        val time: Long,
        val location: Location?,
        val velocity: Float,
        var angular: Float,
        var acceleration: Float,
        var angularVelocity: Float,
        var centrifugal: Float
) {
    override fun toString() = "$id,${DateUtil.date2long(DateUtil.second2date(time))},${location?.latitude},${location?.longitude},$velocity,$angular,$acceleration,$angularVelocity,$centrifugal"
    fun toStringCn() = "$id,时间:${DateUtil.date2long(DateUtil.second2date(time))},位置:${location?.latitude},${location?.longitude},速度$velocity,角度:$angular,加速度:$acceleration,角速度:$angularVelocity,离心力:$centrifugal"
}