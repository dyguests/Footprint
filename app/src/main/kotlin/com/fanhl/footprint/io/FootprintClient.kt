package com.fanhl.footprint.io

import android.content.Context
import com.fanhl.footprint.io.local.service.FileService

class FootprintClient(context: Context) {
    val fileService by lazy { FileService(context) }
}