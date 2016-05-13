package com.fanhl.footprint.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.fanhl.footprint.R
import com.fanhl.footprint.util.sensor.OrientationSensor
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, RecordActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        assignViews()
    }

    private fun assignViews() {
        sensor_btn.setOnClickListener {
            val orientation = OrientationSensor.calculateOrientation()

            orientation_tv.text = "(${orientation[0]},${orientation[1]},${orientation[2]})"
        }
    }
}
