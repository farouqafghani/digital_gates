package com.example.mazintask.util

import com.example.mazintask.ui.common.MyEditText

class ValidationHelper {

    fun validateField(editText: MyEditText?, error: String = "Please fill this field"): Boolean {
        editText?.setError(null)
        if (isValidInput(editText?.text)) {
            return true
        } else {
            editText?.setError(error)
        }
        return false
    }

    private fun isValidInput(input: String?): Boolean {
        return input?.isNotEmpty() ?: false
    }
}
