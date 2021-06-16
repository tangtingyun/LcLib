package com.step.lclib.work.page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.step.lclib.R
import com.step.lclib.work.StorageCase

class StorageActivity : AppCompatActivity() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        StorageActivity.context = this

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        window.decorView.requestFocus()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        findViewById<Button>(R.id.btn_storage).setOnClickListener {
//            StorageCase.test(this)
            StorageCase.insertDept(this)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}