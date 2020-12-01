import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:ui';

class TexturePage extends StatefulWidget {
  @override
  _TexturePageState createState() => _TexturePageState();
}

class _TexturePageState extends State<TexturePage> {
  GlobalKey _globalKey = GlobalKey();

  MethodChannel methodChannel = MethodChannel("Texture");

  Widget _widget;

  int width = 0;
  int height = 0;

  bool b = false;

  int _textureId = 0;

  @override
  void initState() {
    super.initState();

    _widget = Container(
      width: double.infinity,
      height: double.infinity,
      key: _globalKey,
    );

    methodChannel.setMethodCallHandler((call) async {
      print('method: ${call.method},arguments: ${call.arguments}');

      if (call.method == "postNativeTextureId") {
        _textureId = call.arguments;
        return true;
      }
    });
  }

  @override
  void reassemble() {
    super.reassemble();
    print('reassemble .......');
  }

  @override
  Widget build(BuildContext context) {
    print('build ......');
    if (!b) {
      b = !b;
      Future.delayed(Duration(milliseconds: 100), () {
        var width =
            _globalKey.currentContext.findRenderObject().semanticBounds.width;
        var height =
            _globalKey.currentContext.findRenderObject().semanticBounds.height;
        var res = methodChannel.invokeMethod("setWidthHeight", [
          width * window.devicePixelRatio,
          height * window.devicePixelRatio
        ]);
        print('res: $res,_textureId: $_textureId');
        setState(() {
          _widget = Texture(
            textureId: _textureId,
          );
        });
      });
    }

    print('build _widget:${_widget.runtimeType.toString()}');

    return MaterialApp(
      title: "TexturePage",
      home: Scaffold(
        appBar: AppBar(
          title: Text("TexturePage"),
        ),
        body: _widget,
      ),
    );
  }
}
