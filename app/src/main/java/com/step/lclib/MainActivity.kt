package com.step.lclib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.widget.bottomSheet.BottomDialog
import com.step.lclib.widget.dialog.LcDialog
import com.step.lclib.widget.dialog.LcTipDialog
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      testWindowManager()

//      testDialog()

        testBitmap()
    }


    fun testBitmap() {


        findViewById<Button>(R.id.btn_test1).setOnClickListener {

            var bigBitmap = assets.open("aaa.png")
            var decodeBitmap = BitmapFactory.decodeStream(bigBitmap)
            var file = File(externalCacheDir, "test")
            if (!file.exists()) {
                file.mkdir()
            }
            var compressFile = File(file, "compress_big.webp");
            var compressResult = decodeBitmap.compress(
                Bitmap.CompressFormat.WEBP,
                100,
                FileOutputStream(compressFile)
            )
            Log.e(javaClass.simpleName, "testBitmap:   " + compressResult)
            Log.e(javaClass.simpleName, "testBitmap:   " + compressFile.absolutePath)
        }

    }


    fun testWindowManager() {

        findViewById<Button>(R.id.btn_test1).setOnClickListener {
            var textView = TextView(this)
            textView.setText("我是全局添加的")
            textView.setTextColor(Color.BLACK)
            textView.setTextSize(18f)
            textView.setBackgroundColor(Color.RED)
//        var layoutParams = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.TYPE_APPLICATION,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            PixelFormat.TRANSPARENT
//        )
            var layoutParams = WindowManager.LayoutParams()
            windowManager.addView(
                textView, layoutParams
            )
        }

    }

    fun testDialog() {
//        findViewById<LinearLayout>(R.id.ll_main).addView(
//            LcLoadingView(this)
//        )

        findViewById<Button>(R.id.btn_test1).setOnClickListener {
            var create = LcDialog.MessageBuilder(this)
                .create()
            create.show();


            /*     var bottomDialog = BottomDialog.BottomSheetBuilder(this)
                     .setRealView(View.inflate(this, R.layout.dialog_bottom_sheet, null))
                     .build()

                 bottomDialog.show()*/
        }

        findViewById<Button>(R.id.btn_test2).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("发送失败")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

        findViewById<Button>(R.id.btn_test3).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord("提示信息")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

        findViewById<Button>(R.id.btn_test4).setOnClickListener {
            var tipDialog = LcTipDialog.Builder(this)
                .setIconType(LcTipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中")
                .create()
            Log.e("MainActivity", tipDialog.toString())
            tipDialog.show()
        }

    }
}
