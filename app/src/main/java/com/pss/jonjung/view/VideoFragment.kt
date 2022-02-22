package com.pss.jonjung.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.data.db.entity.VideoPost
import com.pss.jonjung.databinding.FragmentToDayRecordWriteBinding
import com.pss.jonjung.databinding.FragmentVideoBinding
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoFragment : BaseFragment<FragmentVideoBinding>(R.layout.fragment_video) {

    val pickImageFromAlbum = 0

    var uriVideo : Uri? = null

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.backImageView.setOnClickListener { this@VideoFragment.findNavController().popBackStack() }

        binding.uploadButton.setOnClickListener {
            if(!TextUtils.isEmpty(binding.videoNameEditText.text ) && uriVideo != null )
            {
                mainViewModel.setVideoPost(VideoPost(binding.videoNameEditText.text.toString(),System.currentTimeMillis().toString()))
                this@VideoFragment.findNavController().popBackStack()

                if(ContextCompat.checkSelfPermission(requireView().context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                    funImageUpload(requireView())
                }

            }
            else Toast.makeText(requireContext(),"제목 , 영상 모두 빈칸을 모두 채워주세요!", Toast.LENGTH_SHORT).show();
        }

        binding.selcectButton.setOnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "video/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum) }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickImageFromAlbum){
            if(resultCode == Activity.RESULT_OK){
                uriVideo = data?.data

                binding.videoPayer.setVideoURI(uriVideo)
                binding.videoPayer.setMediaController(MediaController(context)) // 없으면 에러

                binding.videoPayer.requestFocus() // 준비하는 과정을 미리함

                binding.videoPayer.setOnPreparedListener { Toast.makeText(context, "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
                    binding.videoPayer.start() // 동영상 재개
                }
                binding.videoPayer.setOnCompletionListener {
                    Toast.makeText(context, "동영상의 끝부분입니다!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun funImageUpload(view : View){

        var imgFileName = binding.videoNameEditText.text.toString() + "_.mp4"

        uriVideo?.let { mainViewModel.setVideoUpload(imgFileName,it) }


    }

}
