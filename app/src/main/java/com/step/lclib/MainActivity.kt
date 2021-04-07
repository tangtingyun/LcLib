package com.step.lclib

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.service.RemoteBitmapService
import com.step.lclib.util.DisplayHelper
import com.step.lclib.widget.dialog.LcDialog
import com.step.lclib.widget.dialog.LcTipDialog
import com.step.lclib.widget.popup.LcPopup
import com.step.lclib.widget.popup.LcPopups
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      testWindowManager()

//      testDialog()

//        testBitmap()
//        testBitmap2()

        testPopup();
    }

    private fun testPopup() {
        findViewById<Button>(R.id.btn_popup).setOnClickListener { target ->
            val textView = TextView(this)
            textView.setLineSpacing(DisplayHelper.dp2px(4f).toFloat(), 1.0f)
            val padding: Int = DisplayHelper.dp2px(20f)
            textView.setPadding(padding, padding, padding, padding)
            textView.text = "QMUIBasePopup 可以设置其位置以及显示和隐藏的动画"
            textView.setTextColor(resources.getColor(R.color.color_blue))
            LcPopups.popup(this, DisplayHelper.dp2px(250f))
                .preferredDirection(LcPopup.DIRECTION_TOP)
                .view(textView)
                .edgeProtection(DisplayHelper.dp2px(20f))
                .offsetX(DisplayHelper.dp2px(20f))
                .offsetYIfBottom(DisplayHelper.dp2px(5f))
                .shadow(true)
                .arrow(true)
                .animStyle(LcPopup.ANIM_GROW_FROM_CENTER)
                .onDismiss {
                    Toast.makeText(
                        this,
                        "onDismiss",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show(target);
        }

    }

    private fun testBitmap2() {

        var iRemoteService: IRemoteBitmap? = null;

        findViewById<Button>(R.id.btn_test1).setOnClickListener {
            /*   var decodeResource =
                   BitmapFactory.decodeResource(applicationContext.resources, R.mipmap.ic_launcher)
               findViewById<ImageView>(R.id.iv_remote).setImageBitmap(decodeResource)*/


            var intent = Intent(this, RemoteBitmapService::class.java)

            var connection = object : ServiceConnection {
                //  尴尬 这个方法和onServiceConnected 并不是对应的 unbindService 并不会调用这个方法
                // https://zhuanlan.zhihu.com/p/36892395
                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.e("MainActivity", "onServiceDisconnected")
                    iRemoteService = null
                }

                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    iRemoteService = IRemoteBitmap.Stub.asInterface(service)
                    var remoteBitmap = iRemoteService!!.getRemoteBitmap("")
                    Log.e("MainActivity", "onServiceConnected")
                    findViewById<ImageView>(R.id.iv_remote).setImageBitmap(remoteBitmap)

                    unbindService(this)
                }

            };
            bindService(intent, connection, Context.BIND_AUTO_CREATE);

        }


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
