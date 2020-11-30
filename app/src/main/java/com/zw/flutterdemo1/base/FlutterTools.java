package com.zw.flutterdemo1.base;

import android.content.Context;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

public class FlutterTools {

    public static final String DEFAULT_CACHE_ENGINE_ID = "default_cache_engine_id";

    private static final String METHOD_CHANNEL = "com.cache_engine/method_channel";

    private static FlutterEngine sFlutterEngine;
    private static MethodChannel sMethodChannel;

    public static void preWarmFlutterEngine(Context context) {
        if (null == sFlutterEngine) {
            sFlutterEngine = new FlutterEngine(context);
            sFlutterEngine.getNavigationChannel().setInitialRoute("FlutterEngineCacheApp");

            sFlutterEngine.getDartExecutor().executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            );

            sMethodChannel = new MethodChannel(sFlutterEngine.getDartExecutor(), METHOD_CHANNEL);
            FlutterEngineCache.getInstance().put(DEFAULT_CACHE_ENGINE_ID, sFlutterEngine);
        }
    }

    public static void setInitRoute(String route) {
        sMethodChannel.invokeMethod("setInitRoute", route);
    }

    public static void destroyEngine() {
        if (sFlutterEngine != null) {
            sFlutterEngine.destroy();
        }
    }
}
