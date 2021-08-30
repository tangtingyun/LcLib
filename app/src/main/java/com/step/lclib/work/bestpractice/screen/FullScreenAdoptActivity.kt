package com.step.lclib.work.bestpractice.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.step.lclib.R

import android.annotation.TargetApi
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.*

import android.view.WindowManager


class FullScreenAdoptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        extend_w()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_adopt)
    }


    private fun extend_w() {
        // 延伸显示区域到刘海
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp: WindowManager.LayoutParams = window.getAttributes()
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.setAttributes(lp)
        }
        // 设置页面全屏显示
        val decorView: View = window.getDecorView()
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    private fun extendSupport() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            // 仅当缺口区域完全包含在状态栏之中时，才允许窗口延伸到刘海区域显示
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
            // 永远不允许窗口延伸到刘海区域
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
            // 始终允许窗口延伸到屏幕短边上的刘海区域
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
    }

    private fun extendContentToStatusBarV23(window: Window, extend: Boolean) {
        var visibility = window.decorView.systemUiVisibility
        if (extend) {
            visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else {
            visibility = visibility and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.inv()
        }
        window.decorView.systemUiVisibility = visibility

//        var fixTextColor = false
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
//            fixTextColor = true
//        }
//        var isLight = false
//        if (fixTextColor) {
//            isLight = WindowCompat.getInsetsController(
//                getWindow(),
//                findViewById(android.R.id.content)
//            )!!.isAppearanceLightStatusBars()
//        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        getWindow().setStatusBarColor(Color.TRANSPARENT)
//        if (fixTextColor) {
//            WindowCompat.getInsetsController(
//                getWindow(),
//                findViewById(android.R.id.content)
//            )!!.setAppearanceLightStatusBars(isLight)
//        }
    }

    /**
     * 获得刘海区域信息
     */
    @TargetApi(28)
    fun getNotchParams() {
        val decorView: View = window.decorView
        if (decorView != null) {
            decorView.post(Runnable {
                val windowInsets: WindowInsets = decorView.getRootWindowInsets()
                if (windowInsets != null) {
                    // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
                    val displayCutout = windowInsets.displayCutout
                    Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout!!.safeInsetLeft)
                    Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
                    Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
                    Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)
                    // 获得刘海区域
                    val rects: List<Rect> = displayCutout.boundingRects
                    if (rects == null || rects.size == 0) {
                        Log.e("TAG", "不是刘海屏")
                    } else {
                        Log.e("TAG", "刘海屏数量:" + rects.size)
                        for (rect in rects) {
                            Log.e("TAG", "刘海屏区域：$rect")
                        }
                    }
                }
            })
        }
    }

}