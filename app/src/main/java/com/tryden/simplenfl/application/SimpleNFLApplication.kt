package com.tryden.simplenfl.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.util.*

/**
 * This is the Application class which runs before any class.
 */
@HiltAndroidApp
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