package com.step.lclib.work.page

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.step.lclib.R

class Sample : AppCompatActivity() {


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            var decorView = window.decorView;
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                    or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    or (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                    or (View.SYSTEM_UI_FLAG_FULLSCREEN)
                    or (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val linearLayout = findViewById<LinearLayout>(R.id.ll_sample_root)


        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            .activities
            .filter {
                it.name.startsWith("com.step.lclib.work.page") && it.name != this::class.java.name
            }.map {
                Class.forName(it.name)
            }.forEach { clazz ->
                linearLayout.addView(AppCompatButton(this).apply {
                    isAllCaps = false
                    text = clazz.simpleName
                    setOnClickListener {
                        startActivity(Intent(this@Sample, clazz))
                    }
                })

            }

    }
}