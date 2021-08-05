package com.step.lclib.work.page

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.step.lclib.R
import com.step.lclib.databinding.ActivitySaveBlumBinding
import com.step.lclib.work.utils.AssetsUtils
import com.step.lclib.work.utils.ImageUtils

class SaveBlumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySaveBlumBinding = ActivitySaveBlumBinding.inflate(layoutInflater)
        setContentView(activitySaveBlumBinding.root)

        activitySaveBlumBinding.btnSaveAlbum.setOnClickListener {
//            val bitMap = BitmapFactory.decodeResource(resources, R.drawable.lbxx)
//            ImageUtils.save2Album(this, bitMap)


            AssetsUtils.readStr("xiaoche.txt")
        }
    }
}