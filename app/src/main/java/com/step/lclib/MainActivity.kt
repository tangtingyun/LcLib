package com.step.lclib

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.widget.dialog.LcDialog
import com.step.lclib.widget.dialog.LcTipDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
