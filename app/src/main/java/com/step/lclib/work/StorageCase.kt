package com.step.lclib.work

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.UriUtils
import com.step.lclib.R
import com.step.lclib.work.utils.SavaBlumUtils
import java.io.File
import java.io.FileOutputStream


// https://www.jianshu.com/p/d5573e312bb8
object StorageCase {

    fun insert2(context: Activity) {

        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//        SavaBlumUtils.mediaScan()
    }

//        lclog("${File.pathSeparator}")
//        lclog("${File.separator}")

    fun insertDept(context: Activity) {
        lclog("是否有存储权限16 ${ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED}")

        var insertImage = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            BitmapFactory.decodeResource(context.resources, R.drawable.lbxx),
            "hello_${System.currentTimeMillis()}",
            "i am a test"
        )


//        var file = File(context.externalCacheDir, "dinosaur_${System.currentTimeMillis()}.png")
//        FileIOUtils.writeFileFromIS(
//            file,
//            context.assets.open("dinosaur.png")
//        )
//        SavaBlumUtils.insertImage(
//            context,
//            file.absolutePath,
//            "self_save_${System.currentTimeMillis()}.png",
//            "image/png"
//        )
//        queryAll(context)
    }

    fun queryAll(context: Activity) {


        Thread {
            val projection = arrayOf(
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.MIME_TYPE
            )
            val contentResolver = context.contentResolver
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Images.ImageColumns.DATE_ADDED + " DESC"
            )

            cursor?.let {
                val bucketIndex = it.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val dataIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
                val typeIndex = it.getColumnIndex(MediaStore.Images.Media.MIME_TYPE)
                val sizeIndex = it.getColumnIndex(MediaStore.Images.Media.SIZE)
                val heightIndex = it.getColumnIndex(MediaStore.Images.Media.HEIGHT)
                val widthIndex = it.getColumnIndex(MediaStore.Images.Media.WIDTH)
                val addIndex = it.getColumnIndex(MediaStore.Images.Media.DATE_ADDED)

                while (it.moveToNext()) {
                    val bucket = it.getString(bucketIndex)
                    val data = it.getString(dataIndex)
                    val type = it.getString(typeIndex)
                    val size = it.getString(sizeIndex)
                    val height = it.getString(heightIndex)
                    val width = it.getString(widthIndex)
                    val add = it.getString(addIndex)
                    lclog(
                        """
                        bucket ->                       $bucket
                        data ->                         $data                        
                        type ->                         $type
                        size ->                         $size
                        height ->                       $height
                        width ->                        $width
                        add ->                          $add
                    """.trimIndent()
                    )

                }

                cursor.close()
            }
        }.start()
    }


    fun test(context: Activity) {
        context.apply {


            lclog("系统相册位置: ${MediaStore.Images.Media.EXTERNAL_CONTENT_URI}")
            // 内部存储目录 无需申请权限

            // filesDir  /data/user/0/com.step.lclib/files
            lclog("filesDir  ${filesDir.absolutePath}")

            externalMediaDirs

            // cacheDir  /data/user/0/com.step.lclib/cache
            lclog("cacheDir  ${cacheDir.absolutePath}")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                lclog("dataDir  ${dataDir.absolutePath}")
            }


            // 获取外部私有存储目录 无需申请权限

            // getExternalFilesDir /storage/emulated/0/Android/data/com.step.lclib/files
            lclog("getExternalFilesDir ${getExternalFilesDir("")?.absolutePath}")

            // externalCacheDir /storage/emulated/0/Android/data/com.step.lclib/cache
            lclog("externalCacheDir ${externalCacheDir?.absolutePath}")


            // 获取公有目录 需要权限

            lclog("getExternalStorageDirectory  ${Environment.getExternalStorageDirectory()}")
            lclog(
                "getExternalStoragePublicDirectory ${
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                    )
                }"
            )

            val file = File(Environment.getExternalStorageDirectory(), "MyAppDir")
            if (!file.exists()) {
                lclog("什么鬼 " + file.mkdir().toString())
            }
            useMediaStorage(
                this,
                BitmapFactory.decodeResource(context.resources, R.drawable.dinosaur)
            )

//            testImage(context)
        }
    }


    fun testImage(context: Activity) {


        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            var decodeResource =
                BitmapFactory.decodeResource(context.resources, R.drawable.dinosaur)


            var fileaaa = File(context.cacheDir, "aaa.png")
            decodeResource.compress(
                Bitmap.CompressFormat.PNG,
                100,
                FileOutputStream(fileaaa)
            )

            MediaScannerConnection.scanFile(
                context,
                arrayOf(fileaaa.absolutePath),
                arrayOf("image/png")
            ) { path, uri ->
                lclog("fileaaa  onScanCompleted :  $path  uri-->  $uri")
            }


            var filebbb = File(context.externalCacheDir, "bbb.png")
            decodeResource.compress(
                Bitmap.CompressFormat.PNG,
                100,
                FileOutputStream(filebbb)
            )

            MediaScannerConnection.scanFile(
                context,
                arrayOf(filebbb.absolutePath),
                arrayOf("image/png")
            ) { path, uri ->
                lclog("filebbb  onScanCompleted :  $path  uri-->  $uri")
            }


//            var fileccc = File(
//                Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES
//                ), "ccc.png"
//            )
//            decodeResource.compress(
//                Bitmap.CompressFormat.PNG,
//                100,
//                FileOutputStream(
//                    fileccc
//                )
//            )
//            MediaScannerConnection.scanFile(
//                context,
//                arrayOf(fileccc.absolutePath),
//                arrayOf("image/png")
//            ) { path, uri ->
//                lclog("fileccc  onScanCompleted :  $path  uri-->  $uri")
//            }

            useMediaStorage(context, decodeResource)
        }

        ActivityCompat.requestPermissions(
            context,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            999
        )

    }

    private fun useMediaStorage2(context: Activity, bitmap: Bitmap) {

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DESCRIPTION, "")
            put(MediaStore.Images.Media.DISPLAY_NAME, "")
            put(MediaStore.Images.Media.MIME_TYPE, "")
            put(MediaStore.Images.Media.TITLE, "")
            put(MediaStore.Images.Media.RELATIVE_PATH, "")
        }
    }

    private fun useMediaStorage(context: Activity, bitmap: Bitmap) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an test image")
        values.put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")

        val path = getAppPicturePath()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, path)
        } else {
            val fileDir = File(path)
            if (!fileDir.exists()) {
                fileDir.mkdir()
            }
            values.put(MediaStore.MediaColumns.DATA, path + displayName)
        }
        val external: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val resolver: ContentResolver = context.getContentResolver()

        val insertUri: Uri? = resolver.insert(external, values)
        lclog("insertUri:   $insertUri")
        insertUri?.apply {
            resolver.openOutputStream(this).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
        }
    }

    val displayName = "Image_PPP.png"

    const val APP_FOLDER_NAME = "ExternalScopeTestApp"

    fun getAppPicturePath(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // full path
            "${Environment.getExternalStorageDirectory().absolutePath}/" +
                    "${Environment.DIRECTORY_PICTURES}/$APP_FOLDER_NAME/"
        } else {
            // relative path
            "${Environment.DIRECTORY_PICTURES}/$APP_FOLDER_NAME/"
        }
    }
}