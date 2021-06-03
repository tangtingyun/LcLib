package com.step.lclib.work.page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.step.lclib.R
import com.step.lclib.work.StorageCase
import com.step.lclib.work.VipVideoSaleTitleView

class StorageActivity : AppCompatActivity() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        StorageActivity.context = this

        findViewById<Button>(R.id.btn_storage).setOnClickListener {
            StorageCase.test(this)
        }
    }

    override fun onResume() {
        super.onResume()

        val view = VipVideoSaleTitleView(this)
    }
}