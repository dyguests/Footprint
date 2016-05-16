package com.fanhl.footprint.util

import android.content.Context
import android.os.Environment
import com.fanhl.footprint.model.Foot
import java.io.File
import java.util.*

/**
 * Created by fanhl on 16/5/16.
 */
object FileManager {
    const val PROJECT_FOLDER = "Footprint"

    private fun getExternalDir(context: Context): File {
        val dir = File(Environment.getExternalStorageDirectory().path + PROJECT_FOLDER)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun save(context: Context, list: ArrayList<Foot>) {
        val externalDir = getExternalDir(context)
    }
}