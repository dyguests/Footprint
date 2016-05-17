package com.fanhl.footprint.util

import android.content.Context
import android.os.Environment
import com.fanhl.footprint.model.Foot
import com.fanhl.footprint.model.Record
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 * 文件管理
 *
 * Created by fanhl on 16/5/16.
 */
object FileManager {
    /**整个项目的文件夹*/
    const val PROJECT_FOLDER = "Footprint"
    /**足迹记录文件夹*/
    const val RECORD_FOLDER = "Record"
    /**分析文件文件夹*/
    const val ANALYSIS_FOLDER = "Analysis"

    const val RECORD_EXTENSION_NAME = "txt"//"fpr"

    private fun getExternalDir(context: Context): File {
        val dir = File(Environment.getExternalStorageDirectory().path + "/" + PROJECT_FOLDER)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    private fun getRecordDir(context: Context): File {
        val externalDir = getExternalDir(context)
        val dir = File(externalDir, RECORD_FOLDER)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun save(context: Context, list: ArrayList<Foot>) {
        if (list.isEmpty() || list.size < 2) return

        val file = File(getRecordDir(context), getRecordFileName(list))
        if (file.exists()) file.delete()
        file.createNewFile()

        val writer = FileWriter(file)

        list.forEach { writer.append(it.toString()).append('\n').flush() }

        writer.close()
    }

    /**根据list生成文件名*/
    private fun getRecordFileName(list: ArrayList<Foot>): String {
        val namePrefix = DateUtil.date2str(DateUtil.second2date(list[0].time), DateUtil.FORMAT_LONG_)
        val namePostfix = DateUtil.date2str(DateUtil.second2date(list[list.size - 1].time), DateUtil.FORMAT_hms_)
        return "$namePrefix-$namePostfix.$RECORD_EXTENSION_NAME"
    }

    fun getRecords(context: Context) = getRecordDir(context).listFiles()
            .filter { it.isFile && it.name.endsWith(RECORD_EXTENSION_NAME) }
            .map { it.name }
            .reversed()
            .mapIndexed {
                index, name ->
                Record(index + 1, name)
            }
}