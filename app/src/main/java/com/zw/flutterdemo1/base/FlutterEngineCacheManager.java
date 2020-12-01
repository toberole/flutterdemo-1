package com.zw.flutterdemo1.base;

import android.content.Context;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

public class FlutterEngineCacheManager {
    public static final String FLUTTER_PAGE_ROUTE = "flutter_page_route";

    public static final String DEFAULT_CACHE_ENGINE_ID = "default_cache_engine_id";

    private static final String METHOD_CHANNEL = "com.cache_engine/method_channel";

    private static FlutterEngine flutterEngine;
    private static MethodChannel methodChannel;

    public void init(Context context) {
        if (null == flutterEngine) {
            flutterEngine = new FlutterEngine(context);
            flutterEngine.getNavigationChannel().setInitialRoute("FlutterEngineCacheApp");

            flutterEngine.getDartExecutor().executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            );

            methodChannel = new MethodChannel(flutterEngine.getDartExecutor(), METHOD_CHANNEL);
            FlutterEngineCache.getInstance().put(DEFAULT_CACHE_ENGINE_ID, flutterEngine);
        }
    }

    public void setInitRoute(String route) {
        methodChannel.invokeMethod("setInitRoute", route);
    }

    public void destroyEngine() {
        if (flutterEngine != null) {
            flutterEngine.destroy();
        }
    }

    public static FlutterEngineCacheManager getInstance() {
        return FlutterEngineCacheManagerHolder.instance;
    }

    private static class FlutterEngineCacheManagerHolder {
        static FlutterEngineCacheManager instance = new FlutterEngineCacheManager();
    }
}
