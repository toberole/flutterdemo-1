package com.zw.flutterdemo1

import android.app.Application
import com.zw.flutterdemo1.base.FlutterEngineCacheManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FlutterEngineCacheManager.getInstance().init(this)
    }
}