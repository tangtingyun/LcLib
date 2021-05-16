package com.step.lclib.work

import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.TransitionManager


fun transition(parent: ViewGroup, child: View) {
    var fade = Fade()
    TransitionManager.beginDelayedTransition(parent, fade)
    child.visibility = View.INVISIBLE

}










































