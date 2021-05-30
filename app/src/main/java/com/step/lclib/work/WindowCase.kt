package com.step.lclib.work

import android.content.Context
import android.graphics.PixelFormat
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.getSystemService


/**
 * @author zhangyifei
 * @time 21/5/30 下午4:15
 * @description
 */
object WindowCase {


    fun testAddView(ctx: Context) {

        val windowManager = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager


        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSPARENT
        )


        var button = Button(ctx)

        button.text = "应用内悬浮框"
        windowManager.addView(button, layoutParams)
    }
}