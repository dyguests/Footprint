package com.fanhl.footprint;

import android.hardware.SensorManager;

/**
 * Created by fanhl on 16/5/13.
 */
@Deprecated
public class Mock {
    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];

    void a() {
        float[] values = new float[3];
        float[] R      = new float[9];
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
        SensorManager.getOrientation(R, values);

        // 要经过一次数据格式的转换，转换为度
        values[0] = (float) Math.toDegrees(values[0]);
    }
}
