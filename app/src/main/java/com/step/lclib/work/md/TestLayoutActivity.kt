package com.step.lclib.work.md

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Rect
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.step.lclib.R
import android.view.TouchDelegate
import android.view.View
import android.widget.Toast
import com.step.lclib.work.lclog


class TestLayoutActivity : AppCompatActivity() {
    private lateinit var mTvTopTitle: TextView
    private lateinit var mTvSmallLayout: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
        mTvTopTitle = findViewById(R.id.tv_dialog_vip_pay_success_top)
        mTvSmallLayout = findViewById(R.id.tv_small_layout)
        testTouchDelete(mTvSmallLayout)
        mTvSmallLayout.setOnClickListener {
            Toast.makeText(this, "我是小布局.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testTouchDelete(view: View?) {
        if (view?.parent != null) {
            view.post {
                val parentView: View = view.parent as View
                parentView.post {

                    val rect = Rect()
                    view.getHitRect(rect) //如果太早执行本函数，会获取rect失败，因为此时UI界面尚未开始绘制，无法获得正确的坐标

                    rect.left -= view.left
                    rect.top -= view.top
                    rect.right += view.right
                    rect.bottom += view.bottom
                    lclog("parent rect -->  $rect")

                    parentView.touchDelegate = TouchDelegate(rect, view)
                }
            }
        }
    }

    private fun setTextViewStyles3(textView: TextView) {
        val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE) //颜色的数组
        val position = floatArrayOf(0f, 0.7f, 1.0f) //颜色渐变位置的数组
        val mLinearGradient: LinearGradient = LinearGradient(
            0f,
            0f,
            textView.paint.textSize * textView.text.length,
            0f,
            colors,
            position,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = mLinearGradient
        textView.invalidate()
    }

    private fun setTextViewStyles2(textView: TextView) {
        textView.post {
            val colors = intArrayOf(
                Color.parseColor("#FF6600"),
                Color.parseColor("#FF6600"),
                Color.parseColor("#FF6600"),
                Color.parseColor("#FF2828")
            )
            val position = floatArrayOf(0f, 0.3f, 0.7f, 1.0f)
            val mLinearGradient = LinearGradient(
                0f,
                0f,
                0f,
                textView.height.toFloat(),
                colors,
                position,
                Shader.TileMode.CLAMP
            )
            textView.paint.shader = mLinearGradient
            textView.invalidate()
        }

    }

    private fun setTextViewStyles(textView: TextView) {

        textView.post {
            val mLinearGradient = LinearGradient(
                textView.width.toFloat() / 2,
                0f,
                textView.width.toFloat() / 2,
                textView.height.toFloat(),
                Color.parseColor("#FF6600"),
                Color.parseColor("#FF2828"),
                Shader.TileMode.CLAMP
            )
            textView.paint.shader = mLinearGradient
            textView.invalidate()
        }
    }
}