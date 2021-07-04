package com.step.lclib.work.page

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.step.lclib.R
import com.step.lclib.work.bestpractice.BottomSheet
import com.step.lclib.work.utils.MediaUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MediaActivity : AppCompatActivity() {
    lateinit var mBtn: Button
    lateinit var mImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        mBtn = findViewById(R.id.btn_media)
        mImageView = findViewById(R.id.iv_media)

        mBtn.setOnClickListener {

            testBottomSheet()

        }

    }


    fun testBottomSheet() {
        val bottomSheet = BottomSheet.newInstance()

        bottomSheet.show(supportFragmentManager, "bottom_sheet")
    }

    fun testMedia() {
        //            MediaUtils.loadFirstWithGlide(
//                this, "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4",
//                mImageView, 33000000
//            )

//            MediaUtils.loadImgWithGlide(this, mImageView)

//            MediaUtils.loadCover(mImageView,this)
//            lifecycleScope.launch {
//                withContext(Dispatchers.IO) {
//                    bitmap = MediaUtils.getVideoFrame()
//                    MediaUtils.glideGetVideoFrame(this@MediaActivity) {
//                        mImageView.post {
//                            mImageView.setImageBitmap(it)
//                        }
//                    }
//                }
//            }
    }
}