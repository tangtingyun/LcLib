package com.step.lclib.debug.preference.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.step.lclib.debug.preference.model.PreferenceFile
import com.step.lclib.debug.preference.model.PreferenceItem
import com.step.lclib.debug.preference.model.PreferenceType
import com.step.lclib.debug.preference.ui.DebugPrefActivity
import java.io.File


class DebugPrefManager private constructor(private val context: Context) {


    fun showDebugScreen() {
        DebugPrefActivity.start(context)
    }

    fun getDataBySpName(prefName: String): PreferenceFile {
        val prefsItems = arrayListOf<PreferenceItem>()
        val sharedPreferences = getSpByName(prefName)
        sharedPreferences.all.entries.forEach { obj ->
            obj.value?.apply {
                prefsItems.add(
                    PreferenceItem(
                        obj.key,
                        obj.value!!,
                        sharedPreferences,
                        getValueType(this)
                    )
                )
            }
        }
        return PreferenceFile(sharedPreferences, prefName, prefsItems)
    }

    fun getFilesName(): ArrayList<String> {
        val filesNameList = ArrayList<String>()
        val prefDirList = File(context.applicationInfo.dataDir + "/shared_prefs").list()
        prefDirList?.forEach { fileName ->
            val fileNameWithoutExtension: String =
                fileName.substring(0, TextUtils.indexOf(fileName, ".xml"))
            filesNameList.add(fileNameWithoutExtension)
        }
        return filesNameList
    }

    private fun getSpByName(prefName: String) = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)


    private fun getValueType(value: Any): PreferenceType = when (value) {
        is Boolean -> PreferenceType.Boolean
        is String -> PreferenceType.String
        is Int -> PreferenceType.Integer
        is Float -> PreferenceType.Float
        is Long -> PreferenceType.Long
        else -> PreferenceType.String
    }


    fun putInt(sp: SharedPreferences, key: String, value: Int): SharedPreferences {
        sp.edit().putInt(key, value).apply()
        return sp
    }

    fun putLong(sp: SharedPreferences, key: String?, value: Long): SharedPreferences {
        sp.edit().putLong(key, value).apply()
        return sp
    }


    fun putFloat(sp: SharedPreferences, key: String?, value: Float): SharedPreferences {
        sp.edit().putFloat(key, value).apply()
        return sp
    }

    fun putDouble(sp: SharedPreferences, key: String?, value: Double): SharedPreferences {
        sp.edit().putString(key, value.toString()).apply()
        return sp
    }


    fun putBoolean(sp: SharedPreferences, key: String?, value: Boolean): SharedPreferences {
        sp.edit().putBoolean(key, value).apply()
        return sp
    }


    fun putString(sp: SharedPreferences, key: String?, value: String?): SharedPreferences {
        sp.edit().putString(key, value).apply()
        return sp
    }


    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: DebugPrefManager? = null

        @JvmStatic
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: DebugPrefManager(context.applicationContext).also { instance = it }
            }
    }
}