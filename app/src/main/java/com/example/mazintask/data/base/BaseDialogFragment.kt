package com.example.mazintask.data.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.mazintask.data.callbacks.DialogClickListener

abstract class BaseDialogFragment : DialogFragment() {

    protected open var dialogClickListener: DialogClickListener<*>? = null
    private var isDialogCancelable = true
    private var isWithAnimation = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        setData()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        isCancelable = isDialogCancelable

        return dialog
    }

    protected abstract fun setData()

    protected open fun initListeners() {}

    protected open fun initViews() {}

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(cancelable)
        isDialogCancelable = cancelable
    }


}
