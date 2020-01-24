package com.example.mazintask.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.mazintask.R
import com.example.mazintask.data.manager.SharedPreferenceProvider
import com.example.mazintask.data.models.User
import com.example.mazintask.ui.common.MyEditText
import com.google.gson.Gson


class Utils {

    fun getCircularProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(
            context.getMyColor(R.color.blue_A100),
            context.getMyColor(R.color.blue_A200),
            context.getMyColor(R.color.blue_A400)
        )
        circularProgressDrawable.arrowEnabled = true
        circularProgressDrawable.arrowScale = 5f
        circularProgressDrawable.setArrowDimensions(3f, 3f)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }


    fun clearErrors(vararg editTexts: Any) {
        if (editTexts.isNotEmpty()) {
            for (editText in editTexts) {
                if (editText is MyEditText) {
                    editText.setError(null)
                } else if (editText is EditText) {
                    editText.error = null
                }
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    fun saveUserInMemory(user: User?) {
        val mSharedPreferences = SharedPreferenceProvider.getInstance().sharedPreferences ?: return
        mSharedPreferences.edit()
            .putString(PREF_USER_OBJECT, Gson().toJson(user))
            .commit()
        Log.e("Utils", "Saved successfully")
    }

    val userFromMemory: User?
        get() {
            val mSharedPreferences =
                SharedPreferenceProvider.getInstance().sharedPreferences ?: return null
            return Gson().fromJson(
                mSharedPreferences.getString(PREF_USER_OBJECT, ""), User::class.java
            )
        }
}

const val PREF_USER_OBJECT = "PREF_USER_OBJECT"
