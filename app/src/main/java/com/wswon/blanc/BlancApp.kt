package com.wswon.blanc

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlancApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        KakaoSdk.init(this, BuildConfig.KAKAO_SDK_KEY)
    }

    companion object {
        private lateinit var instance: BlancApp

        fun getInstance(): Application = instance
    }
}