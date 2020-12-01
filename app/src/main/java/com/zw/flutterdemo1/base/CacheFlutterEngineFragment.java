package com.zw.flutterdemo1.base;

import android.os.Bundle;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterFragment;

/**
 * 手动管理Fragment用此类
 */
public class CacheFlutterEngineFragment extends FlutterFragment {

    public static CacheFlutterEngineFragment newInstance(String page_route) {
        MyCachedEngineFragmentBuilder myCachedEngineFragmentBuilder = new MyCachedEngineFragmentBuilder(CacheFlutterEngineFragment.class,
                FlutterEngineCacheManager.DEFAULT_CACHE_ENGINE_ID, page_route);
        CacheFlutterEngineFragment androidFlutterFragment = myCachedEngineFragmentBuilder.build();
        return androidFlutterFragment;
    }

    private static class MyCachedEngineFragmentBuilder extends CachedEngineFragmentBuilder {
        private String page_route;

        protected MyCachedEngineFragmentBuilder(@NonNull Class<? extends FlutterFragment> subclass, @NonNull String engineId, String page_route) {
            super(subclass, engineId);
            this.page_route = page_route;
        }

        @NonNull
        @Override
        protected Bundle createArgs() {
            Bundle bundle = super.createArgs();
            bundle.putString(FlutterEngineCacheManager.FLUTTER_PAGE_ROUTE, page_route);
            return bundle;
        }
    }
}