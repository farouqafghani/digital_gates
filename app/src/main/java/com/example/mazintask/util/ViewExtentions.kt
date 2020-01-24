package com.example.mazintask.util

import android.os.Handler
import android.view.View

fun View?.setOnClickListener2(listener: (view: View) -> Unit) {
    this?.setOnClickListener(View.OnClickListener {
        if (isFastCalls(400)) return@OnClickListener
        Handler().postDelayed({ listener.invoke(this) }, 140)
    })
}

fun View?.setViewVisibility(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}