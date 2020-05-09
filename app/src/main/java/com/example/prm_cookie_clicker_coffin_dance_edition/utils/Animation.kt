package com.example.prm_cookie_clicker_coffin_dance_edition.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.example.prm_cookie_clicker_coffin_dance_edition.R


fun animate(context: Context, obj: View) {
    val animationZoom = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
    val animationZoomOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out)

    obj.startAnimation(animationZoom)
    obj.startAnimation(animationZoomOut)

}