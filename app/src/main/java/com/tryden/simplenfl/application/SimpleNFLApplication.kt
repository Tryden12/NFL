package com.tryden.simplenfl.application

import android.app.Application
import android.content.Context
import java.util.*

class SimpleNFLApplication: Application() {

    companion object {
        lateinit var context: Context
        lateinit var zoneId: String
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        zoneId = TimeZone.getDefault().id
    }
}