package com.zw.flutterdemo1.base;

import android.content.Context;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

/**
 * 一个Native进程只有一个DartVM。
 * <p>
 * 一个DartVM(或说一个Native进程)可以有多个FlutterEngine。
 * <p>
 * 多个FlutterEngine运行在各自的Isolate中，他们的内存数据不共享，需要通过Isolate事先设置的port(顶级函数)通讯。
 * <p>
 * FlutterEngine可以后台运行代码，不渲染UI；也可以通过FlutterRender渲染UI。
 * <p>
 * 初始化第一个FlutterEngine时，DartVM会被创建，之后不会再有其他DartVM环境被创建。
 * <p>
 * FlutterEngine可以通过FlutterEngineCache管理缓存
 * <p>
 * 可以手动改动Flutter项目的入口函数、flutter_assets资源路径、flutter项目初始Route等参数。
 * 涉及到的API有FlutterLoader、DartExecutor、FlutterJNI、Host等等
 */

/**
 * FlutterEngine提供了Dart代码在Android上运行的容器。
 *
 * Dart代码可以在Android后台运，或者通过FlutterRender与FlutterFrameWord配合渲染到屏幕上。
 *
 * Render可以被启动与停止，所以FlutterModule的作用不再只是渲染UI了，当然在有必要时才让Dart代码后台运行。
 *
 * 在一个Android项目中，多FlutterEngine是存在的。每个FlutterEngine运行在不同的isolate中，只能通过IsolatePort进行通讯。
 *
 * 初始化第一个FlutterEngine时，会加载FlutterEngine的native libs并且启动DartVM，DartVM是单例！
 *
 * 创建FlutterEngine之后，使用DartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint)启动Engine。
 */
public class FlutterEngineCacheManager {
    public static final String FLUTTER_PAGE_ROUTE = "flutter_page_route";

    public static final String DEFAULT_CACHE_ENGINE_ID = "default_cache_engine_id";

    private static final String SETINITROUTE_METHOD_CHANNEL = "com.cache_engine/set_initroute_method_channel";
    private static final String SET_INITROUTE = "setInitRoute";

    private static FlutterEngine flutterEngine = null;
    private static MethodChannel methodChannel = null;

    public synchronized void init(Context context) {
        if (null == flutterEngine) {
            flutterEngine = new FlutterEngine(context.getApplicationContext());
            flutterEngine.getNavigationChannel().setInitialRoute("FlutterEngineCacheApp");

            flutterEngine.getDartExecutor().executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            );

            methodChannel = new MethodChannel(flutterEngine.getDartExecutor(), SETINITROUTE_METHOD_CHANNEL);
            FlutterEngineCache.getInstance().put(DEFAULT_CACHE_ENGINE_ID, flutterEngine);
        }
    }

    public synchronized void setInitRoute(String route) {
        if (null != methodChannel) {
            methodChannel.invokeMethod(SET_INITROUTE, route);
        }
    }

    public synchronized void destroyEngine() {
        if (methodChannel != null) {
            methodChannel.setMethodCallHandler(null);
            methodChannel = null;
        }

        if (flutterEngine != null) {
            FlutterEngineCache.getInstance().remove(DEFAULT_CACHE_ENGINE_ID);

            flutterEngine.destroy();
            flutterEngine = null;
        }
    }

    public static FlutterEngineCacheManager getInstance() {
        return FlutterEngineCacheManagerHolder.instance;
    }

    private static class FlutterEngineCacheManagerHolder {
        static FlutterEngineCacheManager instance = new FlutterEngineCacheManager();
    }
}
