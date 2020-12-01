package com.zw.flutterdemo1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zw.flutterdemo1.R
import com.zw.flutterdemo1.base.CacheFlutterEngineFragment

class FragmentActivity : AppCompatActivity() {
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
        tran.add(R.id.container, CacheFlutterEngineFragment.newInstance(ROUTE_PAGE_FRAGMENT))
        tran.commit()
    }
}