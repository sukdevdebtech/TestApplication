package com.testapplication.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton

import com.testapplication.R


abstract class NoNetworkDialog(internal val context: Context) :
    Dialog(context), View.OnClickListener {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.no_network_dialog)
        this.setCancelable(true)
        this.findViewById<AppCompatButton>(R.id.tryAgain).setOnClickListener(this)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lWindowParams = WindowManager.LayoutParams()
        lWindowParams.copyFrom(this.window!!.attributes)
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        this.window!!.attributes = lWindowParams
        show()
    }

    override fun onClick(v: View) {
        dismiss()
        onTryAgain()
    }

    abstract fun onTryAgain()
}
