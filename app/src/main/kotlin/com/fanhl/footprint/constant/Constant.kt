package com.fanhl.footprint.constant

/**
 * Created by fanhl on 16/5/13.
 */
object Constant {
    /**minimum time interval between location updates, in seconds*/
    const val INTERVAL_SECONDS = 1
    /**minimum time interval between location updates, in milliseconds*/
    const val LOCATION_INTERVAL = INTERVAL_SECONDS * 1000L
    /**minimum distance between location updates, in meters*/
    const val LOCATION_STABILIZER_DISTANCE = 0.5f
}