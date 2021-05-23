package com.step.lclib.work

import android.content.Context
import android.media.MediaPlayer


class FlutterAudio private constructor() {
    companion object {
        @JvmStatic
        val INSTANCE: FlutterAudio by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FlutterAudio()
        }
    }

    fun transformData(jsonStr:String){
//        val map: Map<String, Any> =
//            Gson().fromJson(jsonStr, object : TypeToken<HashMap<String?, Any?>?>() {}.getType())
    }

    private var singleMediaPlayer: MediaPlayer? = null


    private var repeatMediaPlayer: MediaPlayer? = null


    fun repeatPlay(context: Context, resName: String) {
        if (resName in context.assets.list("bell") ?: arrayOf()) {
            if (repeatMediaPlayer == null) {
                repeatMediaPlayer = MediaPlayer()
            }
            setUpMediaData(context, repeatMediaPlayer, true, resName)
        }
    }

    fun repeatStop() {

    }

    fun repeatRelease() {
        if (repeatMediaPlayer != null) {
            repeatMediaPlayer?.release()
            repeatMediaPlayer = null
        }
    }

    fun singlePlay(context: Context, resName: String) {
        if (resName in context.assets.list("bell") ?: arrayOf()) {
            if (singleMediaPlayer == null) {
                singleMediaPlayer = MediaPlayer()
            }
            setUpMediaData(context, singleMediaPlayer, false, resName)
        }
    }

    fun singleStop() {

    }


    fun singleRelease() {
        if (singleMediaPlayer != null) {
            singleMediaPlayer?.release()
            singleMediaPlayer = null
        }

    }

    private fun setUpMediaData(
        context: Context,
        mediaPlayer: MediaPlayer?,
        isRepeat: Boolean,
        resName: String
    ) {
        try {
            var openFd = context.assets.openFd("bell/${resName}")
            mediaPlayer?.apply {
                reset()
                // 该方法返回后 需要主动关闭fd
                setDataSource(
                    openFd.fileDescriptor,
                    openFd.startOffset,
                    openFd.length
                )
                openFd.close()
                isLooping = isRepeat
                setOnErrorListener { mp, what, extra ->
                    lclog("Flutter player error: mp  ->  $mp what  ->  $what extra ->  $extra")
                    false
                }
                setOnCompletionListener { mp ->
                    lclog("Flutter player complete")
                }
                setOnPreparedListener {
                    start()
                }
                prepareAsync()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}


object MediaCase {
}