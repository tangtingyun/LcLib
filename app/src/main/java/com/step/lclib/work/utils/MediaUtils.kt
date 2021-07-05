package com.step.lclib.work.utils

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.step.lclib.work.lclog
import java.security.MessageDigest


//http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4

object MediaUtils {

    fun getVideoFrame(): Bitmap {
        val mediaMetadataRetriever = MediaMetadataRetriever()

        mediaMetadataRetriever.setDataSource(
            "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4",
            mutableMapOf()
        )
        val frameAtTime = mediaMetadataRetriever.getFrameAtTime(33000000)
        lclog("frameAtTime  ->  $frameAtTime")

        return frameAtTime!!;
    }

    fun glideGetVideoFrame(context: Context, cb: (Bitmap) -> Unit) {
        var requestOptions = RequestOptions.frameOf(33000000)
        requestOptions = requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST)
        GlideApp.with(context)
            .asBitmap()
            .load("http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4")
            .apply(requestOptions)
            .into(object : CustomTarget<Bitmap?>() {


                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    resource.let(cb)
                }

            });

    }


    /**
     * context 上下文
     * uri 视频地址
     * imageView 设置image
     * frameTimeMicros 获取某一时间帧
     */
    fun loadFirstWithGlide(
        context: Context,
        uri: String?,
        imageView: ImageView,
        frameTimeMicros: Long
    ) {
        val requestOptions = RequestOptions.frameOf(frameTimeMicros)
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST)
        requestOptions.transform(object : BitmapTransformation() {
            override fun transform(
                @NonNull pool: BitmapPool,
                @NonNull toTransform: Bitmap,
                outWidth: Int,
                outHeight: Int
            ): Bitmap {
                return toTransform
            }

            override fun updateDiskCacheKey(messageDigest: MessageDigest) {
                try {
                    messageDigest.update(
                        (context.packageName + "RotateTransform").toByteArray(
                            charset("utf-8")
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        Glide.with(context).load(uri).apply(requestOptions).into(imageView)
    }

    // http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg
    fun loadImgWithGlide(
        context: Context,
        imageView: ImageView,
        uri: String = "http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"
    ) {

        GlideApp.with(context)
            .load(uri)
            .into(imageView)

    }


    /**
     * 加载第四秒的帧数作为封面
     * url就是视频的地址
     */
    fun loadCover(imageView: ImageView, context: Context) {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(4000000)
                    .centerCrop()
            )
            .load("http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4")
            .into(imageView)
    }
}