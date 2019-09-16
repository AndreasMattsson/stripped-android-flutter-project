package com.example

import android.os.Bundle
import io.flutter.embedding.android.DrawableSplashScreen
import io.flutter.embedding.android.SplashScreen

class TestFlutterActivity: io.flutter.embedding.android.FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun provideSplashScreen(): SplashScreen? {
        return DrawableSplashScreen(getDrawable(android.R.drawable.sym_def_app_icon)!!) as SplashScreen
    }
}