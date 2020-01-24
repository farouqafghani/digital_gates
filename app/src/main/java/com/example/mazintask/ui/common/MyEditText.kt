package com.example.mazintask.ui.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import com.example.mazintask.R
import com.example.mazintask.util.getMyText
import kotlinx.android.synthetic.main.edittext_custom.view.*

/**
 * Created by Farouq Afghani on 09/05/2019.
 */
open class MyEditText : FrameLayout {

    val text: String
        get() = editText.getMyText()

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        isSaveEnabled = true
        View.inflate(context, R.layout.edittext_custom, this)
        setTextWatcherListener()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditText)
        val text = typedArray.getString(R.styleable.MyEditText_android_text)
        val hint = typedArray.getString(R.styleable.MyEditText_android_hint)

        val minLines = typedArray.getInt(R.styleable.MyEditText_android_minLines, -1)
        val maxLines= typedArray.getInt(R.styleable.MyEditText_android_maxLines, -1)

        val drawableEnd = typedArray.getResourceId(R.styleable.MyEditText_android_drawableEnd, 0)
        val drawableStart = typedArray.getResourceId(R.styleable.MyEditText_android_drawableStart, 0)

        val inputType = typedArray.getInteger(
            R.styleable.MyEditText_android_inputType, -1/*InputType.TYPE_CLASS_TEXT*/
        )

        val textSize = typedArray.getDimensionPixelSize(
            R.styleable.MyEditText_android_textSize, -1/*R.dimen.font_medium_plus*/
        )

        val imeOptions = typedArray.getInteger(
            R.styleable.MyEditText_android_imeOptions, -1/*EditorInfo.IME_ACTION_DONE*/
        )

        val passwordToggleEnabled =
            typedArray.getBoolean(R.styleable.MyEditText_passwordToggleEnabled, false)

        if (minLines != -1)  editText?.minLines = minLines
        if (maxLines != -1)  editText?.maxLines = maxLines

        setHint(hint)
        setText(text)
        setInputType(inputType)
        setTextSize(textSize)
        setImeOption(imeOptions)
        setPasswordVisibilityToggleEnabled(passwordToggleEnabled)
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, 0, drawableEnd, 0)
        editText.gravity = Gravity.START or Gravity.TOP

        typedArray.recycle()
        editText.gravity = Gravity.START or Gravity.TOP
    }

    fun getEditText(): EditText {
        return editText
    }


    fun setText(text: String?) {
        editText?.setText(text)
    }

    fun setHint(text: String?) {
        textInputLayout?.hint = text
    }

    fun setInputType(inputType: Int) {
        if (inputType == -1) return
        editText?.inputType = inputType
    }

    fun setTextSize(textSize: Int) {
        if (textSize == -1) return
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(textSize))
    }

    fun setImeOption(imeOption: Int) {
        if (imeOption == -1) return
        editText.imeOptions = imeOption
    }

    fun setError(error: String?) {
        textInputLayout?.error = error
        textInputLayout?.isErrorEnabled = !error.isNullOrEmpty()
    }

    private fun setPasswordVisibilityToggleEnabled(showPassword: Boolean) {
        textInputLayout?.isPasswordVisibilityToggleEnabled = showPassword
    }

    private fun setTextWatcherListener() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                setError(null)
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}
