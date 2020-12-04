package com.zw.flutterdemo1.activity

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.Surface
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs.BackgroundMode
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterTextureView
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.TextureRegistry

class TextureActivity : FlutterActivity() {
    companion object {
        var method_postNativeTextureId = "postNativeTextureId"
        var method_set_width_height = "setWidthHeight"

        var TAG = TextureActivity::class.java.simpleName
    }

    private lateinit var methodChannel: MethodChannel

    private lateinit var surface: Surface

    private var textureId: Long = -1

    private var b: Boolean = false

    private var w: Double = 0.0
    private var h: Double = 0.0

    override fun getInitialRoute(): String {
        return "TextureActivity"
    }

    override fun getBackgroundMode(): BackgroundMode {
        return BackgroundMode.opaque
    }

    override fun onFlutterSurfaceViewCreated(flutterSurfaceView: FlutterSurfaceView) {
        super.onFlutterSurfaceViewCreated(flutterSurfaceView)
        Log.e(TAG, "onFlutterSurfaceViewCreated ......")
        init()
    }

    override fun onFlutterTextureViewCreated(flutterTextureView: FlutterTextureView) {
        super.onFlutterTextureViewCreated(flutterTextureView)
        Log.e(TAG, "onFlutterTextureViewCreated ......")
    }

    override fun onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed()
    }

    private fun init() {

        methodChannel = MethodChannel(flutterEngine?.dartExecutor, "Texture")

        val textures: TextureRegistry = flutterEngine!!.renderer
        val textureEntry = textures.createSurfaceTexture()
        textureId = textureEntry.id()
        Log.e(TAG, "init textureId: $textureId")
        val surfaceTexture = textureEntry.surfaceTexture()
        surface = Surface(surfaceTexture)

        var res = object : MethodChannel.Result {
            override fun success(result: Any?) {
                Log.e(TAG, "success result: $result")
            }

            override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                Log.e(
                    TAG,
                    "error errorCode: $errorCode,errorMessage: $errorMessage,errorDetails: $errorDetails"
                )
            }

            override fun notImplemented() {
                Log.e(TAG, "notImplemented")
            }
        }

        methodChannel.setMethodCallHandler { call, result ->
            if (method_set_width_height == call.method) {
                var list = call.arguments as List<Double>
                w = list[0]
                h = list[1]
                Log.e(TAG, "arguments w: $w,h: $h")
                res.success(true)
                drawUI()
            }
        }
        methodChannel.invokeMethod(method_postNativeTextureId, textureId, res)
    }

    private fun drawUI() {
        val rect = Rect(0, 0, w.toInt(), h.toInt())
        val color = Color.parseColor("#FF0000")
        val paint = Paint()
        paint.setAntiAlias(false) //设置画笔为无锯齿
        paint.setColor(Color.RED) //设置画笔颜色
        window.decorView.postDelayed({
            var canvas = surface.lockCanvas(rect)
            Log.i(TAG, "drawUI ......")

            canvas.drawColor(Color.WHITE) //白色背景
            paint.setStrokeWidth(3.0.toFloat()) //线宽
            paint.setStyle(Paint.Style.STROKE) //空心效果
            canvas.drawCircle(150F, 350F, 50F, paint) //绘制圆形

            surface.unlockCanvasAndPost(canvas)
        }, 1000)
//        Thread {
//            Thread.sleep(1000)
//            while (!b) {
//                // 渲染太快容易异常
//                Thread.sleep(5)
//                var canvas = surface.lockCanvas(rect)
//                Log.i(TAG, "drawUI ......")
//
//                canvas.drawColor(Color.WHITE) //白色背景
//                paint.setStrokeWidth(3.0.toFloat()) //线宽
//                paint.setStyle(Paint.Style.STROKE) //空心效果
//                canvas.drawCircle(150F, 350F, 50F, paint) //绘制圆形
//
//                surface.unlockCanvasAndPost(canvas)
//            }
//        }.start()
    }
}