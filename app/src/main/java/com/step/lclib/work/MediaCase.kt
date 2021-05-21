package com.step.lclib.work

import android.Manifest
import android.content.Context
import android.media.MediaPlayer

object MediaCase {
    fun play(context: Context, resName: String) {
        var openFd = context.assets.openFd(resName)

        val mediaPlayer = MediaPlayer()

        mediaPlayer.reset()


        mediaPlayer.setDataSource(
            openFd.fileDescriptor,
            openFd.startOffset,
            openFd.length
        )


        mediaPlayer.isLooping = true

        mediaPlayer.prepare()
        mediaPlayer.start()
    }
}