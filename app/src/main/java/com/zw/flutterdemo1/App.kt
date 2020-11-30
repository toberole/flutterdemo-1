package com.zw.flutterdemo1

import android.app.Application
import com.zw.flutterdemo1.base.FlutterTools

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FlutterTools.preWarmFlutterEngine(this)
    }
}