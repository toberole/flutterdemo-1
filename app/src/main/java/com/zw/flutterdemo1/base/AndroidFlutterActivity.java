package com.zw.flutterdemo1.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterSurfaceView;
import io.flutter.embedding.android.FlutterTextureView;

public class AndroidFlutterActivity extends FlutterActivity {

    static final String EXTRA_CACHED_ENGINE_ID = "cached_engine_id";
    static final String EXTRA_ROUTE = "extra_route";
    static final String EXTRA_DESTROY_ENGINE_WITH_ACTIVITY = "destroy_engine_with_activity";

    public static void open(Context context, String route) {
        Intent intent = new Intent(context, AndroidFlutterActivity.class)
                .putExtra(EXTRA_CACHED_ENGINE_ID, FlutterTools.DEFAULT_CACHE_ENGINE_ID)
                .putExtra(EXTRA_ROUTE, route)
                .putExtra(EXTRA_DESTROY_ENGINE_WITH_ACTIVITY, false); // Activity 销毁时保留 FlutterEngine
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onDestroy");
    }

    @Override
    public void onFlutterSurfaceViewCreated(@NonNull FlutterSurfaceView flutterSurfaceView) {
        super.onFlutterSurfaceViewCreated(flutterSurfaceView);
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onFlutterSurfaceViewCreated");
    }

    @Override
    public void onFlutterTextureViewCreated(@NonNull FlutterTextureView flutterTextureView) {
        super.onFlutterTextureViewCreated(flutterTextureView);
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onFlutterTextureViewCreated");
    }

    @Override
    public void onFlutterUiNoLongerDisplayed() {
        super.onFlutterUiNoLongerDisplayed();
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "#onFlutterUiNoLongerDisplayed");
    }

    @Override
    public void onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed();
        // 设置 Flutter 界面入口，注意不要在 onCreate 方法中调用，否则Flutter 入口不会更新。
        String route = getIntent().getStringExtra(EXTRA_ROUTE);
        FlutterTools.setInitRoute(route);
    }
}
