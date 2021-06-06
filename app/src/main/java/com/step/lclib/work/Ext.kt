package com.step.lclib.work

import android.util.Log


const val GLOBAL_TAG = "LCPAPA"


fun Any.lclog(msg: String?) {
    Log.e(GLOBAL_TAG, "${Thread.currentThread()} ---> ${msg ?: ""}")
}