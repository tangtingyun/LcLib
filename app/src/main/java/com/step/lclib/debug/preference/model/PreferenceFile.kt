package com.step.lclib.debug.preference.model

import android.content.SharedPreferences

data class PreferenceFile(
    val sharedPreferences: SharedPreferences,
    val fileName: String,
    val items: List<PreferenceItem>
)