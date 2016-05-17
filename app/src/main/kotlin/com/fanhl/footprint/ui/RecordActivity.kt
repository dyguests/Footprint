package com.fanhl.footprint.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fanhl.footprint.R
import com.fanhl.footprint.model.Foot
import com.fanhl.footprint.service.FootprintService
import com.fanhl.footprint.ui.base.BaseActivity
import com.fanhl.footprint.util.DateUtil
import com.fanhl.footprint.util.sensor.LocationSensor
import com.fanhl.footprint.util.sensor.OrientationSensor
import kotlinx.android.synthetic.main.activity_record.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RecordActivity : BaseActivity() {
    lateinit var orientationSensor: OrientationSensor
    lateinit var locationSensor: LocationSensor

    val foots = listOf<Foot>()

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, RecordActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        assignViews()
        initData()

        EventBus.getDefault().register(this);
    }

    override fun onResume() {
        super.onResume()
        locationSensor.enable()
        orientationSensor.enable()
    }

    override fun onPause() {
        super.onPause()
        locationSensor.disable()
        orientationSensor.disable()
    }

    private fun assignViews() {
        if (FootprintService.isActive) record_tb.isChecked = true

        record_tb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) FootprintService.start(this@RecordActivity)
            else FootprintService.stop(this@RecordActivity)
        }
    }

    private fun initData() {
        locationSensor = LocationSensor(this)
        orientationSensor = OrientationSensor(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN) fun onEvent(foot: Foot) {
        id_tv.text = "${getString(R.string.id)}:${foot.id}"
        time_tv.text = "${getString(R.string.time)}:${DateUtil.date2long(DateUtil.second2date(foot.time))}"
        location_tv.text = "${getString(R.string.location)}:${foot.location}"
        velocity_tv.text = "${getString(R.string.velocity)}:${foot.velocity}"
        angular_tv.text = "${getString(R.string.angular)}:${foot.angular}"
        acceleration_tv.text = "${getString(R.string.acceleration)}:${foot.acceleration}"
        angular_velocity_tv.text = "${getString(R.string.angular_velocity)}:${foot.angularVelocity}"
        centrifugal_tv.text = "${getString(R.string.centrifugal)}:${foot.centrifugal}"
    }
}
