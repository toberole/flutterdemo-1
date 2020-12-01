package com.zw.flutterdemo1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zw.flutterdemo1.activity.Fragment1Activity
import com.zw.flutterdemo1.activity.FragmentActivity
import com.zw.flutterdemo1.activity.NativeActivity
import com.zw.flutterdemo1.activity.TextureActivity
import com.zw.flutterdemo1.base.CacheFlutterEngineActivity
import com.zw.flutterdemo1.base.FlutterEngineCacheManager
import io.flutter.embedding.engine.renderer.FlutterRenderer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val ROUTE_PAGE_A = "/page_a"
        const val ROUTE_PAGE_B = "/page_b"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn_FragmentActivity.setOnClickListener(this)
        btn_Fragment1Activity.setOnClickListener(this)
        btn_initEngine.setOnClickListener(this)
        btn_destroyEngine.setOnClickListener(this)
        btn_NativeActivity.setOnClickListener(this)
        btn_TextureActivity.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn1 -> {
                CacheFlutterEngineActivity.open(this, ROUTE_PAGE_A)
            }

            R.id.btn2 -> {
                CacheFlutterEngineActivity.open(this, ROUTE_PAGE_B)
            }

            R.id.btn_FragmentActivity -> {
                val intent = Intent(this, FragmentActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_Fragment1Activity -> {
                val intent = Intent(this, Fragment1Activity::class.java)
                startActivity(intent)
            }

            R.id.btn_initEngine -> {
                FlutterEngineCacheManager.getInstance().init(this.applicationContext)
            }

            R.id.btn_destroyEngine -> {
                FlutterEngineCacheManager.getInstance().destroyEngine()
            }

            R.id.btn_NativeActivity -> {
                val intent = Intent(this, NativeActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_TextureActivity -> {
                val intent = Intent(this, TextureActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun test() {

    }
}