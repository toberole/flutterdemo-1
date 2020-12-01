import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/test/page_a.dart';
import 'package:flutter_module/test/page_b.dart';
import 'package:flutter_module/test/page_fragment.dart';

class FlutterEngineCacheApp extends StatefulWidget {
  @override
  _FlutterEngineCacheAppState createState() => _FlutterEngineCacheAppState();
}

class _FlutterEngineCacheAppState extends State<FlutterEngineCacheApp> {
  MethodChannel _methodChannel =
      MethodChannel('com.cache_engine/set_initroute_method_channel');
  
  Widget _page = DefaultHomePage();

  @override
  void initState() {
    super.initState();
    print('FlutterEngineCacheApp#initState');
    _methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case 'setInitRoute':
          _handleInitRouteMethodCall(call);
          break;
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    print('FlutterEngineCacheApp#dispose');
  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'FlutterEngineCacheApp',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: _page,
    );
  }

  void _handleInitRouteMethodCall(MethodCall call) async {
    switch (call.arguments) {
      case '/page_a':
        _page = PageA();
        break;
      case '/page_b':
        _page = PageB();
        break;
      case '/page_fragment':
        _page = PageFragment();
        break;
      default:
        _page = DefaultHomePage();
        break;
    }
    setState(() {});
  }
}

class DefaultHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: GestureDetector(
          child: Icon(Icons.arrow_back),
          onTap: () => SystemNavigator.pop(),
        ),
        title: Text('Default Home Page'),
      ),
      body: Container(
        color: Colors.white,
      ),
    );
  }
}
