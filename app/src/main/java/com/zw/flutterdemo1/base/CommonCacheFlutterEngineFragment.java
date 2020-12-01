package com.zw.flutterdemo1.base;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterFragment;

/**
 * 使用navigator管理fragment可以用此类
 * 手动管理Fragment也可以用此类
 */
public class CommonCacheFlutterEngineFragment extends FlutterFragment {
    @Nullable
    @Override
    public String getCachedEngineId() {
        return FlutterEngineCacheManager.DEFAULT_CACHE_ENGINE_ID;
    }

    @Override
    public void onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed();
        String route = getArguments().getString(FlutterEngineCacheManager.FLUTTER_PAGE_ROUTE);
        if (TextUtils.isEmpty(route)) {
            route = getPageRoute();
        }

        if (TextUtils.isEmpty(route)) {
            throw new RuntimeException("page route must not be null");
        }

        FlutterEngineCacheManager.getInstance().setInitRoute(route);
    }

    protected String getPageRoute() {
        return "";
    }
} 