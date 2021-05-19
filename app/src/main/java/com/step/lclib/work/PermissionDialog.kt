package com.step.lclib.work

import android.util.Log


interface OnPermissionResult {
    fun granted()

    fun rejected()
}


object PermissionDialog {

    @JvmOverloads
    @JvmStatic
    fun request(permissions: Array<Char>, info: String, cb: OnPermissionResult) {
        permissions.filter {
            it.toInt() > 'd'.toInt()
        }.takeIf { it.isNotEmpty() }?.let {
            Log.e(
                AppConstant.TAG,
                "getMainThreadExecutor: ${it}"
            )
        }
    }
}