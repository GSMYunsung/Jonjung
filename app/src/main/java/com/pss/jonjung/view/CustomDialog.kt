package com.pss.jonjung.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.pss.jonjung.R

class CustomDialog(context: Context)
{
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog(title : String, content : String)
    {
        dialog.setContentView(R.layout.star_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.findViewById<TextView>(R.id.title_textview2).text = title
        dialog.findViewById<TextView>(R.id.content_textview2).text = content
        dialog.setCancelable(true)
        dialog.show()



        dialog.findViewById<Button>(R.id.button).setOnClickListener {
            dialog.dismiss()
        }

    }

    interface OnDialogClickListener
    {
        fun onClicked(name: String)
    }

}