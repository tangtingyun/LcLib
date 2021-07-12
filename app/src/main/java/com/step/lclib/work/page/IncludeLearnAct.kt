package com.step.lclib.work.page

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.step.lclib.R

class IncludeLearnAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_include_learn)

        var includeRoot = findViewById<LinearLayout>(R.id.include_root)
//        var includeRoot = findViewById<LinearLayout>(R.id.include_refactor)

        var includeTv = findViewById<TextView>(R.id.tv_include)

        var motherBtn = findViewById<Button>(R.id.btn_include_mother)


//        includeRoot.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        includeTv.setTextColor(Color.BLACK)
        includeTv.text = "内部btn223"

        motherBtn.text = "Mother btn"


    }
}