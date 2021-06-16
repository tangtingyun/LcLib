package com.step.lclib.work.ktx

import android.text.TextUtils

fun String?.ok() = !TextUtils.isEmpty(this)


fun String?.empty() = TextUtils.isEmpty(this)