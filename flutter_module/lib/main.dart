import 'dart:ui';

import 'package:flutter/material.dart';

import 'base/flutter_engine_cache_app.dart';

void main() {
  String defaultRouteName = window.defaultRouteName;
  print('defaultRouteName: $defaultRouteName');

  if (defaultRouteName == 'FlutterEngineCacheApp') {
    return runApp(FlutterEngineCacheApp());
  }
}
