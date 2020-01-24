package com.example.mazintask.util

import android.text.SpannableString
import android.widget.TextView

/**
 * This method will return trimmed string from TextView
 */
fun TextView?.getMyText(): String {
    return this?.text?.toString()?.trim() ?: ""
}

fun TextView?.setMyText(text: Any?, goneIfNullOrEmpty: Boolean = false) {
    if (text != null) {
        if (text is Int || text is String) {
            this?.text = text.toString()
            if (goneIfNullOrEmpty) this.setViewVisibility(text.toString().isNotEmpty())
        } else if (text is SpannableString) {
            val text1 = text as SpannableString?
            this?.text = text1
        }
    } else {
        this?.text = ""
        if (goneIfNullOrEmpty) this.setViewVisibility(false)
    }
}