package com.step.lclib.work.bestpractice.threeD

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.step.lclib.R
import com.step.lclib.databinding.ActivityCardFlipWithCameraBinding

// https://sourcegraph.com/github.com/genzeb/flip/-/blob/src/com/tekle/oss/android/animation/FlipAnimation.java
// https://medium.com/geekculture/how-to-add-card-flip-animation-in-the-android-app-3060afeadd45
class CardFlipWithCamera : AppCompatActivity() {
    lateinit var mActivityCardBinding: ActivityCardFlipWithCameraBinding
    var isBack = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityCardBinding =
            ActivityCardFlipWithCameraBinding.inflate(layoutInflater)
        setContentView(mActivityCardBinding.root)

        mActivityCardBinding.btnFlipCamera.setOnClickListener {
            flipCard(
                this,
                if (isBack) mActivityCardBinding.viewCardBack.root else mActivityCardBinding.viewCardFront.root,
                if (isBack) mActivityCardBinding.viewCardFront.root else mActivityCardBinding.viewCardBack.root
            )
        }
    }

    fun flipCard(
        context: Context,
        visibleView: View,
        inVisibleView: View
    ) {

        try {

            visibleView.visibility = View.VISIBLE

            val scale = context.resources.displayMetrics.density

            val cameraDist = 8000 * scale

            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist


            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context, R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)

            val flipInAnimationSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet

            flipInAnimationSet.setTarget(visibleView)

            flipOutAnimatorSet.start()
            flipInAnimationSet.start()
            flipInAnimationSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    isBack = !isBack
                    inVisibleView.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }
            })


        } catch (e: Exception) {

        }
    }


}