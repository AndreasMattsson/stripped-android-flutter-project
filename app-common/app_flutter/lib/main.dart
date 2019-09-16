import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

final systemOverlayStyle = SystemUiOverlayStyle.light.copyWith(
  systemNavigationBarColor: Colors.white,
  systemNavigationBarIconBrightness: Brightness.dark,
);

@pragma('vm:entry-point')
void main() {
  print("SampleApp main");
  SystemChrome.setSystemUIOverlayStyle(systemOverlayStyle);
  runApp(SampleApp());
}


class SampleApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final isIos = Theme.of(context).platform == TargetPlatform.iOS;

    debugPrint("SampleApp build");

    final root = MaterialApp(
      home: Scaffold(
        body: SizedBox.expand(
          child: LayoutBuilder(
            builder: (_, __) {
              debugPrint("LayoutBuilder build");
              return Center(
                child: Text(
                  "Hello World",
                  style: TextStyle(color: Colors.red),
                ),
              );
            },
          ),
        ),
      ),
    );

    return root;
  }
}
