package com.step.lclib.work

import android.R.attr.bitmap
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.IOException
import java.io.OutputStream


object AndroidQ {

    fun savePic(context: Context, bitmap: Bitmap) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image")
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "Image.png")
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis().toString() + ".png")
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/me")

        val external: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val resolver: ContentResolver = context.contentResolver
        val insertUri: Uri? = resolver.insert(external, values)
        if (insertUri != null) {
            resolver.openOutputStream(insertUri).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, it)
            }
        }
    }
}