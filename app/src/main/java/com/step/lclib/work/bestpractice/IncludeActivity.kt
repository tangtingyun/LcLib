package com.step.lclib.work.bestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewStub
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.step.lclib.R
import me.ele.uetool.UETool


// https://segmentfault.com/a/1190000015384836
// https://segmentfault.com/a/1190000014636827  深度优先
class IncludeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_include)

        val mInnerTv = findViewById<TextView>(R.id.tv_include_inner)

        val mLlIncludeRoot = findViewById<LinearLayout>(R.id.ll_include_root)

        val mBtn = findViewById<Button>(R.id.btn_include_test)


        mLlIncludeRoot.setBackgroundColor(resources.getColor(R.color.colorAccent))



        mInnerTv.text = "我被外部修改了"

        mBtn.setOnClickListener {
            val importPanel: LinearLayout? =
                findViewById<ViewStub>(R.id.vs_place_holder)?.inflate() as? LinearLayout

            importPanel?.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        }

        UETool.showUETMenu();
    }
}