package com.step.lclib.debug.preference.model

import android.content.SharedPreferences

data class PreferenceItem(
    val key: String,
    var value: Any,
    val sp: SharedPreferences,
    val type: PreferenceType
)