package com.zw.flutterdemo1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zw.flutterdemo1.R
import com.zw.flutterdemo1.base.FlutterEngineCacheManager
import com.zw.flutterdemo1.base.CommonCacheFlutterEngineFragment

class Fragment1Activity : AppCompatActivity() {
    companion object {
        const val ROUTE_PAGE_FRAGMENT = "/page_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        supportActionBar?.hide()
        initView()
    }

    private fun initView() {
        var tran = supportFragmentManager.beginTransaction()
        var f = CommonCacheFlutterEngineFragment()
        var bundle = Bundle()
        bundle.putString(FlutterEngineCacheManager.FLUTTER_PAGE_ROUTE, ROUTE_PAGE_FRAGMENT)
        f.arguments = bundle
        tran.add(R.id.container, f)
        tran.commit()
    }
}