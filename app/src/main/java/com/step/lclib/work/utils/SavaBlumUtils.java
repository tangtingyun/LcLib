package com.step.lclib.work.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.UriUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SavaBlumUtils {

    public static final String insertImage(ContentResolver cr, String imagePath,
                                           String name, String mimeType) throws FileNotFoundException {
        return insertImage(cr, imagePath, name, mimeType, "drivingtest");
    }

    public static final String insertImage(ContentResolver cr, String imagePath,
                                           String name, String mimeType, String bucketName) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(imagePath);
        try {

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, name);
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            values.put(MediaStore.Images.Media.MIME_TYPE, mimeType);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + bucketName + File.separator);
            }

            Uri url = null;
            String stringUrl = null;    /* value to be returned */

            try {
                url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                FileInputStream fileInputStream = new FileInputStream(imagePath);
                FileIOUtils.writeFileFromIS(UriUtils.uri2File(url), fileInputStream);
            } catch (Exception e) {
                Log.d("SavaBlumUtils", "Failed to insert image" + e);
                if (url != null) {
                    cr.delete(url, null, null);
                    url = null;
                }
            }
            if (url != null) {
                stringUrl = url.toString();
            }
            return stringUrl;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

}
