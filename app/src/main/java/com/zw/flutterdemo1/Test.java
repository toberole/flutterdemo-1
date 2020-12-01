package com.zw.flutterdemo1;

import android.graphics.SurfaceTexture;

import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.view.TextureRegistry;

public class Test {
    public void test() {
        FlutterRenderer render = null;
        FlutterJNI flutterJNI = null;
        TextureRegistry.SurfaceTextureEntry surfaceTextureEntry = render.createSurfaceTexture();
        SurfaceTexture surfaceTexture = surfaceTextureEntry.surfaceTexture();

        FlutterEngine flutterEngine = null;
        FlutterView flutterView = null;
        flutterView.attachToFlutterEngine(flutterEngine);
    }
} 