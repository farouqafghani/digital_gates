package com.example.mazintask.data.callbacks

import android.app.Dialog

interface DialogClickListener<T> {
    fun onPositiveButtonClick(myObj: T?, dialog: Dialog?)
    fun onNegativeButtonClick() {}
}