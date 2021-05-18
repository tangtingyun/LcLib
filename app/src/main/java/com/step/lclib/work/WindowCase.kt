package com.step.lclib.work

import android.content.Context
import android.graphics.PixelFormat
import android.view.WindowManager
import androidx.core.content.getSystemService

object WindowCase {

    fun testAddView(ctx: Context) {

        val windowManager = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager


        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSPARENT
        )


    }
}