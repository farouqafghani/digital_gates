package com.example.mazintask.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.LocaleList
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.mazintask.R
import com.example.mazintask.data.callbacks.DialogClickListener
import com.example.mazintask.ui.common.dialog.GeneralDialog
import com.google.gson.GsonBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


fun Context?.toast(message: String) {
    this ?: return
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showAlertMessage(
    title: String? = null, message: String, callback: (() -> Unit)? = null
) {
    try {
        val activity: Activity? = when (this) {
            is Activity -> this
            is ContextThemeWrapper -> this.baseContext as Activity
            else -> null
        }

        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        GeneralDialog().setTitle(title ?: getMyString(R.string.label_alert)).setDescription(message)
            .setDialogCancelable(false).setPositiveButton(getMyString(R.string.label_back))
            .setDialogClickListener(object : DialogClickListener<Void> {
                override fun onPositiveButtonClick(myObj: Void?, dialog: Dialog?) {
                    dialog?.dismiss()
                    callback?.invoke()
                }
            }).show(fragmentManager, "")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun isFastCalls(timeDelta: Long = 200): Boolean {
    val clickTime = System.currentTimeMillis()
    if (clickTime - GeneralExtensions.lastClickTime < timeDelta) {
        GeneralExtensions.lastClickTime = clickTime
        return true
    }
    GeneralExtensions.lastClickTime = clickTime
    return false
}

fun <T> List<T>?.getSubList(fromIndex: Int, toIndex: Int): List<T> {
    if (this != null && this.size >= toIndex) {
        return subList(fromIndex, toIndex)
    }
    return this ?: ArrayList()
}


fun Context?.getMyColor(@ColorRes color: Int, alphaRatio: Float? = null): Int {
    this ?: return color
    return if (alphaRatio == null){
        ContextCompat.getColor(this, color)
    }else{
        val newColor: Int
        val alpha = (Color.alpha(color) * alphaRatio).roundToInt()
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        newColor
    }
}

fun Context?.getMyString(@StringRes stringRes: Int, language: String? = null): String {
    this ?: return ""
    try {
        if (language.isNullOrEmpty()) {
            // return string by default language
            return getString(stringRes)
        } else {
            // return string for specific language
            val resources = resources
            val configuration = Configuration(resources.configuration)
            val defaultLocale = Locale(language)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(defaultLocale)
                configuration.setLocales(localeList)
                createConfigurationContext(configuration).getString(stringRes)
            } else {
                configuration.setLocale(defaultLocale)
                createConfigurationContext(configuration).getString(stringRes)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}

fun DialogFragment.showDialogFragment(fragmentManager: FragmentManager?) {
    if (isDialogShowing()) {
        dismissAllowingStateLoss()
    }
    fragmentManager ?: return
    show(fragmentManager, "")
}

fun DialogFragment.isDialogShowing(): Boolean {
    if (isAdded || isVisible || isResumed || dialog?.isShowing == true) {
        return true
    }
    return false
}

fun getFragmentManager(context: Context?): FragmentManager? {
    val activity: Activity? = when (context) {
        is Activity -> context
        is ContextThemeWrapper -> context.baseContext as Activity
        else -> null
    }
    return try {
        (activity as FragmentActivity).supportFragmentManager
    } catch (e: Exception) {
        null
    }
}

fun Boolean.toInt() = if (this) 1 else 0
fun Int.toBoolean() = this == 1

fun String?.removeSpacesBetweenLines(): String {
    val text = this ?: ""
    return text.replace(Regex("(?m)^[ \t]*\r?\n"), "")
}

fun Context?.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextThemeWrapper -> baseContext as Activity
        else -> null
    }
}

fun printPrettyGson(objToPrint: Any) {
    try {
        val json = GsonBuilder().setPrettyPrinting().create().toJson(objToPrint)
        println(json)
    } catch (e: Exception) {
    }
}


object GeneralExtensions {
    var lastClickTime: Long = 0
}

