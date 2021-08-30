package com.step.lclib.work.md

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.step.lclib.R
import com.step.lclib.databinding.ActivityMaterialBinding
import com.step.lclib.work.page.UtilsTestActivity

class MaterialActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
//        hide1()
        hide2()
//        hide3()
        super.onCreate(savedInstanceState)
        mBinding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.ivShape.setOnClickListener {
            val intent = Intent(this, UtilsTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun hide4() {
        if (Build.VERSION.SDK_INT >= 30) {
            var controller = ViewCompat.getWindowInsetsController(window.decorView)
            // 设置状态栏反色
            controller?.isAppearanceLightStatusBars = true
            // 取消状态栏反色
            controller?.isAppearanceLightStatusBars = false
            // 设置导航栏反色
            controller?.isAppearanceLightNavigationBars = true
            // 取消导航栏反色
            controller?.isAppearanceLightNavigationBars = false
            // 隐藏状态栏
            controller?.hide(WindowInsetsCompat.Type.statusBars())
            // 显示状态栏
            controller?.show(WindowInsetsCompat.Type.statusBars())
            // 隐藏导航栏
            controller?.hide(WindowInsetsCompat.Type.navigationBars())
            // 显示导航栏
            controller?.show(WindowInsetsCompat.Type.navigationBars())
            // 同时隐藏状态栏和导航栏
            controller?.hide(WindowInsetsCompat.Type.systemBars())
            // 同时隐藏状态栏和导航栏
            controller?.show(WindowInsetsCompat.Type.systemBars())
        }

    }

    private fun hide3() {
        if (Build.VERSION.SDK_INT >= 30) {
            val controller = window.decorView.windowInsetsController
            // 设置状态栏反色
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            // 取消状态栏反色
            controller?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            // 设置导航栏反色
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
            // 取消导航栏反色
            controller?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
            // 同时设置状态栏和导航栏反色
            controller?.setSystemBarsAppearance(
                (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS),
                (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
            )
            // 同时取消状态栏和导航栏反色
            controller?.setSystemBarsAppearance(
                0,
                (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
            )
            // 隐藏状态栏
            controller?.hide(WindowInsets.Type.statusBars())
            // 显示状态栏
            controller?.show(WindowInsets.Type.statusBars())
            // 隐藏导航栏
            controller?.hide(WindowInsets.Type.navigationBars())
            // 显示导航栏
            controller?.show(WindowInsets.Type.navigationBars())
            // 同时隐藏状态栏和导航栏
            controller?.hide(WindowInsets.Type.systemBars())
            // 同时隐藏状态栏和导航栏
            controller?.show(WindowInsets.Type.systemBars())
        }


    }

    private fun hide2() {
        val decorView = window.decorView;
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                or (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                or (View.SYSTEM_UI_FLAG_FULLSCREEN)
                or (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
    }

    private fun hide1() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}