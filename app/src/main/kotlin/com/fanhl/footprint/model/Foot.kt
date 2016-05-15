package com.fanhl.footprint.model

import android.location.Location

data class Foot(
        val id: Int,
        val time: Long,
        val location: Location,
        val velocity: Float,
        var orientation: Float,
        var acceleration: Float,
        var angularVelocity: Float,
        var centrifugal: Float
)