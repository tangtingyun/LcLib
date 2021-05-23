package com.step.lclib.work.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.step.lclib.R
import com.step.lclib.work.StorageCase

class StorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        findViewById<Button>(R.id.btn_storage).setOnClickListener {
            StorageCase.test(this)
        }
    }
}