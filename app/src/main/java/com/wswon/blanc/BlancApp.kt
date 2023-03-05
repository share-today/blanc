package com.wswon.blanc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlancApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: BlancApp

        fun getInstance(): Application = instance
    }
}