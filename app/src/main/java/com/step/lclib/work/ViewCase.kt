package com.step.lclib.work

import android.view.View
import android.view.ViewGroup

object ViewCase {

    fun testFindViewById(view: ViewGroup) {
        view.findViewById<View>(View.NO_ID)
    }
}