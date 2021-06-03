package com.step.lclib.work.exo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.step.lclib.R


//  https://exoplayer.dev/hello-world.html
// https://developer.android.com/codelabs/exoplayer-intro#2
class ExoActivity : AppCompatActivity() {
    private val playerView: StyledPlayerView by lazy {
        findViewById<StyledPlayerView>(R.id.exo_play_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo)
        setUpExoPlayer()
    }

    private fun setUpExoPlayer() {
        var simplePlayer = SimpleExoPlayer.Builder(this).build()
        playerView.player = simplePlayer

        val mediaItem = MediaItem.fromUri("file:///android_asset/local_video.mp4")

        simplePlayer.setMediaItem(mediaItem)

        simplePlayer.prepare()
        simplePlayer.play()


    }
}