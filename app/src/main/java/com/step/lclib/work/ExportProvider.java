package com.step.lclib.work;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

public class ExportProvider extends FileProvider {

    public static Uri file2uri(Context context, File file) {
        // 7.0和以上版本的系统要通过 FileProvider 创建一个 content 类型的 Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".work.ExportProvider", file);
        } else {
            return Uri.fromFile(file);

        }
    }
}
