package com.fanhl.footprint;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.fanhl.footprint.model.Foot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 16/5/13.
 */
@Deprecated
public class Mock {
    public static final String s = "1";
    public static final String TAG = Mock.class.getSimpleName();
    void a(Context context) {
        // 获取系统LocationManager服务
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 从GPS获取最近的定位信息
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        // 设置每2秒获取一次GPS的定位信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000, 8, new LocationListener() {

                    @Override
                    public void onLocationChanged(Location location) {
                        // 当GPS定位信息发生改变时，更新位置
                        //updateView(location);
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        //updateView(null);
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        // 当GPS LocationProvider可用时，更新位置
                        //updateView(locationManager.getLastKnownLocation(provider));
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }
                });
    }

    void b(){
        List<Foot> list = new ArrayList<>();
    }
}
