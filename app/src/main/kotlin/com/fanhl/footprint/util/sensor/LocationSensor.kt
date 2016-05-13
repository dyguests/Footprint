package com.fanhl.footprint.util.sensor

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.fanhl.footprint.constant.Constant

/**
 * 位置传感器(GPS)
 * Created by fanhl on 16/5/13.
 */
class LocationSensor(context: Context) {
    val TAG = LocationSensor::class.java.name

    /** 获取系统LocationManager服务*/
    val locationManager: LocationManager
    /** 从GPS获取最近的定位信息*/
    var currentLocation: Location
    var lastLocation: Location

    init {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        // 设置每秒获取一次GPS的定位信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                Constant.LOCATION_INTERVAL, Constant.LOCATION_STABILIZER_DISTANCE, object : LocationListener {

            override fun onLocationChanged(location: Location) {
                Log.d(TAG, location.toString())
                // 当GPS定位信息发生改变时，更新位置
                //updateView(location);
                lastLocation = currentLocation
                currentLocation = location
            }

            override fun onProviderDisabled(provider: String) {
                //updateView(null);
            }

            override fun onProviderEnabled(provider: String) {
                // 当GPS LocationProvider可用时，更新位置
                //updateView(locationManager.getLastKnownLocation(provider));
                lastLocation = currentLocation
                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }
        })

    }

    fun getLocation() = Location2(currentLocation, lastLocation)

    data class Location2(val currentLocation: Location, val lastLocation: Location)
}
