import 'package:flutter/material.dart';

class PageFragment extends StatefulWidget {
  @override
  _PageFragmentState createState() => _PageFragmentState();
}

class _PageFragmentState extends State<PageFragment> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("PageFragment"),
      ),
    );
  }
}
