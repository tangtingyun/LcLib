package com.step.lclib

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.widget.dialog.LcDialog
import com.step.lclib.widget.dialog.LcTipDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btn_test1).setOnClickListener {
            testWindowManager()
        }

    }


    fun testWindowManager() {

        var textView = TextView(this)
        textView.setText("我是全局添加的")
        textView.setTextColor(Color.BLACK)
        textView.setTextSize(18f)
        textView.setBackgroundColor(Color.RED)
//        var layoutParams = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.TYPE_APPLICATION,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            PixelFormat.TRANSPARENT
//        )
        var layoutParams = WindowManager.LayoutParams()
        windowManager.addView(
            textView, layoutParams
        )

    }

    fun testDialog() {
//        findViewById<LinearLayout>(R.id.ll_main).addView(
//            LcLoadingView(this)
//        )

        findViewById<Button>(R.id.btn_test1).setOnClickListener {
            var create = LcDialog.MessageBuilder(this)
                .create()
            create.show();
        }

        findViewById<Button>(R.id.btn_test2).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("发送失败")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

        findViewById<Button>(R.id.btn_test3).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord("提示信息")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

        findViewById<Button>(R.id.btn_test4).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

    }
}
