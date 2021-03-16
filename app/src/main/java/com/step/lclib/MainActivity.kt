package com.step.lclib

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.widget.dialog.LcTipDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<LinearLayout>(R.id.ll_main).addView(
//            LcLoadingView(this)
//        )
        findViewById<Button>(R.id.btn_test).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("发送成功")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }
    }
}
