package com.fanhl.footprint.ui.base

import android.support.v7.app.AppCompatActivity
import com.fanhl.footprint.App

/**
 * Created by fanhl on 16/5/5.
 */
abstract class BaseActivity : AppCompatActivity() {
    val app: App
        get() = application as App
}
