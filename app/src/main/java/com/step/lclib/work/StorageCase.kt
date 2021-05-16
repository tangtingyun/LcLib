package com.step.lclib.work

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log

object StorageCase {

    fun test(context: Context) {
        context.apply {
            // 内部存储目录 无需申请权限

            // filesDir  /data/user/0/com.step.lclib/files
            Log.e(AppConstant.TAG, "filesDir  ${filesDir.absolutePath}")

            // cacheDir  /data/user/0/com.step.lclib/cache
            Log.e(AppConstant.TAG, "cacheDir  ${cacheDir.absolutePath}")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Log.e(AppConstant.TAG, "dataDir  ${dataDir.absolutePath}")
            }


            // 获取外部私有存储目录 无需申请权限

            // getExternalFilesDir /storage/emulated/0/Android/data/com.step.lclib/files
            Log.e(AppConstant.TAG, "getExternalFilesDir ${getExternalFilesDir("")?.absolutePath}")

            // externalCacheDir /storage/emulated/0/Android/data/com.step.lclib/cache
            Log.e(AppConstant.TAG, "externalCacheDir ${externalCacheDir?.absolutePath}")


            // 获取公有目录 需要权限

//            Environment.getExternalStorageDirectory()
//            Environment.getExternalStoragePublicDirectory("")
        }
    }
}