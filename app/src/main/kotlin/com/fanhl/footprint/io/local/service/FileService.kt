package com.fanhl.footprint.io.local.service

import android.content.Context
import com.fanhl.footprint.model.Record
import com.fanhl.footprint.util.FileManager
import rx.lang.kotlin.observable

class FileService(context: Context) {
    private var context: Context

    init {
        this.context = context
    }

    fun getRecords() = observable<List<Record>> {
        it.onNext(FileManager.getRecords(context))
        it.onCompleted()
    }
}