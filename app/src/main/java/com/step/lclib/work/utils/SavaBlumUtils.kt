package com.step.lclib.work.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.UriUtils
import com.step.lclib.work.lclog
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException


//https://www.jianshu.com/p/660fa631ed25
//https://blog.csdn.net/chuyouyinghe/article/details/116279102
//https://www.jianshu.com/p/d0c77b9dc527
//https://developer.android.google.cn/training/data-storage/shared/media
//https://ppting.me/2020/04/19/2020_04_19_how_to_use_Android_MediaStore_Api/
//https://www.hurryyu.com/2020/04/17/%E8%BD%BB%E6%9D%BE%E9%80%82%E9%85%8DAndroid%2010%20Scoped%20Storage%20%E5%88%86%E5%8C%BA%E5%AD%98%E5%82%A8%20-%20%E5%8D%9A%E5%AE%A2%E7%89%88/#%E9%97%AE%E9%A2%98%E5%9B%9B%E3%80%81%E7%9C%9F%E7%9A%84%E4%B8%8D%E8%83%BD%E5%9C%A8%E5%A4%96%E9%83%A8%E5%AD%98%E5%82%A8%E6%92%92%E9%87%8E%E4%BA%86%EF%BC%9F%E6%88%91%E4%B8%8D%E4%BF%A1
object SavaBlumUtils {

    const val APP_GALLERY_FOLDER = "lechen"

    fun getAppPicturePath(context: Context): String {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            // relative path
            "${Environment.DIRECTORY_PICTURES}" +
                    "${File.separator}" +
                    "$APP_GALLERY_FOLDER" +
                    "${File.separator}"

        } else {
            // full path
            "${context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath}" +
                    "${File.separator}" +
                    "$APP_GALLERY_FOLDER" +
                    "${File.separator}"
        }
    }

    fun insertImage(
        context: Context, imagePath: String,
        name: String, mimeType: String
    ) {
        val stream = FileInputStream(imagePath)
        val contentResolver = context.contentResolver

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, name)
        values.put(MediaStore.Images.Media.DISPLAY_NAME, name)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        val appPicturePath = getAppPicturePath(context)
        lclog("appPicturePath ->   ${appPicturePath}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(
                MediaStore.Images.Media.RELATIVE_PATH,
                appPicturePath
            );
        } else {
            values.put(MediaStore.Images.Media.DATA, appPicturePath)
        }
        var url: Uri? = null
        try {
            url = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            url?.apply {
                copy(contentResolver.openOutputStream(this), stream)
            }
        } catch (e: Exception) {
            url?.apply {
                contentResolver.delete(this, null, null)
            }
            Log.d("SavaBlumUtils", "Failed to insert image$e")
        }
    }
}