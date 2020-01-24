package com.example.mazintask.ui.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mazintask.R
import com.example.mazintask.data.base.BaseDialogFragment
import com.example.mazintask.data.callbacks.DialogClickListener
import com.example.mazintask.util.setOnClickListener2
import kotlinx.android.synthetic.main.dialog_general.*

/**
 * Created by Farouq Afghani on 19/9/2018.
 */
class GeneralDialog : BaseDialogFragment() {

    private var title = ""
    private var description = ""
    private var positiveButton = ""
    private var negativeButton = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_general, container, false)
    }

    override fun setData() {
        setText(tvTitle, title)
        setText(tvDescription, description)
        setText(tvPositive, positiveButton)
        setText(tvNegative, negativeButton)
    }

    private fun setText(textView: TextView?, text: String) {
        textView?.text = text
        textView?.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun initListeners() {
        tvPositive?.setOnClickListener2{
            if (dialogClickListener == null) dismiss()
            dialogClickListener?.onPositiveButtonClick(null, dialog)
        }
        tvNegative?.setOnClickListener2{
            dismiss()
            dialogClickListener?.onNegativeButtonClick()
        }
    }

    fun setTitle(title: String) = apply { this.title = title }

    fun setDescription(description: String) = apply { this.description = description }

    fun setPositiveButton(positiveButton: String) = apply { this.positiveButton = positiveButton }

    fun setNegativeButton(negativeButton: String) = apply { this.negativeButton = negativeButton }

    fun setDialogCancelable(isCancelable: Boolean) = apply { this.isCancelable = isCancelable }

    fun setDialogClickListener(dialogClickListener: DialogClickListener<*>) =
        apply { this.dialogClickListener = dialogClickListener }
}
