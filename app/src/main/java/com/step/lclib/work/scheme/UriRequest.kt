package com.step.lclib.work.scheme

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.ArrayMap
import com.step.lclib.work.AppGlobals
import java.util.*

class UriRequest {
    val uri: String = "";
    val context: Context = AppGlobals.getApplication();
    val mExtras: ArrayMap<String, Any?> = ArrayMap()


    fun parseUriSafely(uri: String?): Uri {
        return if (TextUtils.isEmpty(uri)) Uri.EMPTY else Uri.parse(uri)
    }

    fun <T> putField(key: String, value: T?): UriRequest {
        if (value != null) {
            mExtras.put(key, value)
        }
        return this
    }

    fun <T> putFieldIfAbsent(key: String, value: T): UriRequest {
        if (!mExtras.containsKey(key)) {
            mExtras.put(key, value)
        }
        return this
    }

    fun putFields(fields: HashMap<String, Any>): UriRequest {
        if (fields != null) {
            mExtras.putAll(fields)
        }
        return this
    }

    fun hasField(key: String): Boolean {
        return mExtras.containsKey(key)
    }

    fun getIntField(key: String, defaultValue: Int = 0): Int {
        return getField(key, defaultValue)
    }

    fun getLongField(key: String, defaultValue: Long = 0): Long {
        return getField(key, defaultValue)
    }

    fun getBooleanField(key: String, defaultValue: Boolean = false): Boolean {
        return getField(key, defaultValue)
    }

    fun getStringField(key: String, defaultValue: String = ""): String {
        return getField(key, defaultValue)
    }


    private fun <T> getField(key: String, defaultValue: T): T {
        val field: Any? = mExtras.get(key)
        return field as? T ?: defaultValue
    }
}