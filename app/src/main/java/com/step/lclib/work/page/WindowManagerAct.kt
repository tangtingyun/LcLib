package com.step.lclib.work.page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.step.lclib.R
import com.step.lclib.work.WindowCase

class WindowManagerAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_manager)

        findViewById<Button>(R.id.btn_jump).setOnClickListener {
//            Intent(this, StorageActivity::class.java).apply {
//                startActivity(this)
//            }
            test(this)
        }

    }


    fun test(ctx: Context) {
        WindowCase.testAddView(ctx)
    }
}