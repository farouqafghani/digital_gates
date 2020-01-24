package com.example.mazintask.data.base

import android.annotation.SuppressLint
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mazintask.ui.common.dialog.LoadingDialog
import retrofit2.Response

/**
 * Created by Farouq Afghani on 25/04/2019.
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog()
    }

    protected fun showLoadingDialog(message: String? = "", isCancelable: Boolean = false) {
        hideLoadingDialog {
            loadingDialog.isCancelable = isCancelable
            loadingDialog.setMessage(message ?: "").show(supportFragmentManager, "")
        }
    }

    fun hideLoadingDialog(
        withDelay: Boolean = false, onDismissListener: (() -> Unit)? = null) {
        if (loadingDialog.isVisible || loadingDialog.isAdded) {
            if (withDelay) {
                Handler().postDelayed({
                    loadingDialog.dismissAllowingStateLoss()
                    onDismissListener?.invoke()
                }, 400)
            } else {
                loadingDialog.dismissAllowingStateLoss()
                onDismissListener?.invoke()
            }
        }else {
            onDismissListener?.invoke()
        }
    }


    protected fun handleOnFailure(t: Throwable, actionListener: (() -> Unit)? = null) {
        hideLoadingDialog()
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show();
//        ServerErrorUtils.handleOnFailure(context = this, t = t, actionListener = actionListener)
    }

    protected fun handleErrorResponse(response: Response<*>) {
//        ServerErrorUtils.handleErrorResponse(this, response)
    }
}
