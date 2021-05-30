package com.step.lclib.work.page

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.step.lclib.R
import com.step.lclib.work.WindowCase

class Sample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)


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
            WindowCase.testAddView(this)
        }


    }

    override fun onResume() {
        super.onResume()

    }
}