package com.example.mazintask.data.manager

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceProvider {

    var sharedPreferences: SharedPreferences? = null
        private set

    companion object {
        @Volatile
        private var instance: SharedPreferenceProvider? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SharedPreferenceProvider().also { instance = it }
            }
    }

    fun init(context: Context?) {
        sharedPreferences = context?.applicationContext?.getSharedPreferences("STATIC_PREF", Context.MODE_PRIVATE)
    }
}