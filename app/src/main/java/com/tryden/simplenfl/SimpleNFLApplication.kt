package com.tryden.simplenfl

import android.app.Application
import android.content.Context

class SimpleNFLApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}