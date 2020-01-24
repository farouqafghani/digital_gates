package com.example.mazintask.ui.common.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mazintask.R
import com.example.mazintask.data.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Created by Farouq Afghani on 19/9/2018.
 */
class LoadingDialog : BaseDialogFragment() {

    var dialogInterface: DialogInterface? = null
    var message = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun setData() {
        setText(tvMessage, message)
    }

    private fun setText(textView: TextView?, text: String) {
        textView?.text = text
        textView?.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
    }

    fun setMessage(message: String) = apply {
        this.message = message
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogInterface?.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialogInterface?.cancel()
    }
}
