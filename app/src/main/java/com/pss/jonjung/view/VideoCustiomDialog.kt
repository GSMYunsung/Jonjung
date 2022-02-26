package com.pss.jonjung.view

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.pss.jonjung.R
import com.pss.jonjung.viewmodel.PostViewModel

class VideoCustiomDialog(context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog(title : String,context: Context,uri : Uri,viewmodel : PostViewModel,fragment : Fragment)
    {
        dialog.setContentView(R.layout.video_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.findViewById<VideoView>(R.id.video_view).setVideoURI(uri)
        dialog.findViewById<TextView>(R.id.video_title_textview).text = title
        dialog.findViewById<VideoView>(R.id.video_view).setMediaController(MediaController(context)) // 없으면 에러

        dialog.findViewById<VideoView>(R.id.video_view).requestFocus() // 준비하는 과정을 미리함

        dialog.findViewById<VideoView>(R.id.video_view).setOnPreparedListener { Toast.makeText(context, "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
            dialog.findViewById<VideoView>(R.id.video_view).start() // 동영상 재개
        }
        dialog.findViewById<VideoView>(R.id.video_view).setOnCompletionListener {
            Toast.makeText(context, "동영상의 끝부분입니다!", Toast.LENGTH_SHORT).show()
        }
        dialog.setCancelable(true)
        dialog.show()



        dialog.findViewById<ImageView>(R.id.back_imageView).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<ImageView>(R.id.imageView6).setOnClickListener {

            viewmodel.vidioName.observe(fragment,{
                viewmodel.deleteVideoPost(viewmodel.videoPost.value!!)
                viewmodel.deleteVideo(viewmodel.vidioName.value!!)
                Log.d("cocopam",viewmodel.vidioName.value!!)
                dialog.dismiss()
            })

        }

    }

    interface OnDialogClickListener
    {
        fun onClicked(name: String)
    }
}