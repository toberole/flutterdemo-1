package com.zw.flutterdemo1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zw.flutterdemo1.base.AndroidFlutterActivity
import com.zw.flutterdemo1.base.FlutterTools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val ROUTE_PAGE_A = "/page_a"
        const val ROUTE_PAGE_B = "/page_b"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn1 -> {
                AndroidFlutterActivity.open(this, ROUTE_PAGE_A)
            }
            R.id.btn2 -> {
                AndroidFlutterActivity.open(this, ROUTE_PAGE_B)
            }
        }
    }

}