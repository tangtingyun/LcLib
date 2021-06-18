package com.step.lclib.work.page

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.step.lclib.R
import com.step.lclib.work.lclog
import cn.eclicks.drivingtest.utils.DtHelper
import java.nio.charset.Charset
import java.util.*

class Sample : AppCompatActivity() {


//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            var decorView = window.decorView;
//            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//                    or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//                    or (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//                    or (View.SYSTEM_UI_FLAG_FULLSCREEN)
//                    or (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val linearLayout = findViewById<LinearLayout>(R.id.ll_sample_root)


        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            .activities
            .filter {
                it.name.startsWith("com.step.lclib.work") && it.name != this::class.java.name
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

        window.decorView.post {
//            WindowCase.testAddView(this)
        }


    }

    override fun onResume() {
        super.onResume()

        lclog("current year ->  ${getCurrentDayOfYear()}")

        // 只有需要给答案，那么才加载答案
        val answer = "bc54ee1d7bbc78633e2033cea9257e69"
        var realAnswer = ""
        try {
            val tmp: ByteArray = DtHelper.hexStringToBytes(answer)
            val out: ByteArray =
                DtHelper.answer(this, tmp, System.currentTimeMillis(), 1)
            realAnswer = String(out, Charset.forName("UTF-8"))
        } catch (e: Exception) {
        }

        lclog("realAnswer ->   $realAnswer")
    }

    //获取指定时间的一年中的第几天
    fun getDayOfYear(currentTimeMillis: String): Int {
        val calendar = Calendar.getInstance()
        val date = Date(currentTimeMillis.toLong())
        calendar.time = date
        return calendar[Calendar.DAY_OF_YEAR]
    }

    fun getCurrentDayOfYear(): Int {
        return Calendar.getInstance()[Calendar.DAY_OF_YEAR]
    }
}