package com.step.lclib.work

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import com.step.lclib.R
import java.io.File
import java.io.FileOutputStream

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


    fun testImage(context: Activity) {
        var decodeResource = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)


        decodeResource.compress(
            Bitmap.CompressFormat.PNG,
            100,
            FileOutputStream(File(context.cacheDir, "aaa.png"))
        )
        decodeResource.compress(
            Bitmap.CompressFormat.PNG,
            100,
            FileOutputStream(File(context.externalCacheDir, "bbb.png"))
        )

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            decodeResource.compress(
                Bitmap.CompressFormat.PNG,
                100,
                FileOutputStream(File(context.cacheDir, "ccc.png"))
            )
        }

        ActivityCompat.requestPermissions(
            context,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            999
        )

    }
}