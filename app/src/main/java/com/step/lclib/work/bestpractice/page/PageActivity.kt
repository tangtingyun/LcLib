package com.step.lclib.work.bestpractice.page

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.step.lclib.R
import com.step.lclib.databinding.ActivityConstraintlayoutBinding
import com.step.lclib.databinding.ActivityPageBinding
import com.step.lclib.work.flow.WanApiService
import com.step.lclib.work.lclog
import com.step.lclib.work.retrofit
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.ByteArrayOutputStream


class PageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var constraintlayoutBinding = ActivityConstraintlayoutBinding.inflate(layoutInflater)

        setContentView(constraintlayoutBinding.root)


        constraintlayoutBinding.viewMiddleBg.post {
            var layoutParams =
                constraintlayoutBinding.viewMiddleBg.layoutParams as MarginLayoutParams

            layoutParams.topMargin =
                constraintlayoutBinding.viewTopBg.height - constraintlayoutBinding.viewMiddleBg.height / 3
            constraintlayoutBinding.viewMiddleBg.requestLayout()
        }


        var inflateBinding = ActivityPageBinding.inflate(layoutInflater)
        setContentView(inflateBinding.root)

        inflateBinding.ivPngOne.setImageResource(R.drawable.riziyan_favicon)


        var decodeResource = BitmapFactory.decodeResource(resources, R.drawable.riziyan_favicon)

        lifecycleScope.launch(Dispatchers.IO) {
            var byteArrayOutputStream = ByteArrayOutputStream()
            decodeResource.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
            var toByteArray = byteArrayOutputStream.toByteArray()
            var decodeByteArrayBitmap =
                BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.size)
            withContext(Dispatchers.Main) {
                inflateBinding.ivPngTwo.setImageBitmap(decodeByteArrayBitmap)
            }

        }

    }
}