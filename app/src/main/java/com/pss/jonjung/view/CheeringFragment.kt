package com.pss.jonjung.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentCheeringBinding
import com.pss.jonjung.databinding.FragmentNoticeBoardBinding
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheeringFragment : BaseFragment<FragmentCheeringBinding>(R.layout.fragment_cheering) {

    private val VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/jonjung-6f6f7.appspot.com/o/images%2F%E1%84%92%E1%85%AA%E1%84%86%E1%85%A7%E1%86%AB%20%E1%84%80%E1%85%B5%E1%84%85%E1%85%A9%E1%86%A8%202022-01-16%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%201.51.38.mov?alt=media&token=8bc6ad3c-a27a-44ef-b31e-60daf140932e"

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        // 에뮬레이터로 확인하려면 내 프로젝트에 동영상 파일이 있어야 됨
        var uri: Uri = Uri.parse(VIDEO_URL)

         binding.videoView.setVideoURI(uri)
         binding.videoView.setMediaController(MediaController(requireContext())) // 없으면 에러

         binding.videoView.requestFocus() // 준비하는 과정을 미리함

         binding.videoView.setOnPreparedListener { Toast.makeText(requireContext(), "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
             binding.videoView.start() // 동영상 재개
         }
         binding.videoView.setOnCompletionListener {
             Toast.makeText(requireContext(), "동영상 시청 완료", Toast.LENGTH_SHORT).show()
         }

        binding.btnStart.setOnClickListener {
                binding.videoView.start() // 동영상 재개
         }
         binding.btnResume.setOnClickListener {
             binding.videoView.resume() // 동영상 처음부터 재시작
         }
         binding.btnPause.setOnClickListener { binding.videoView.pause() // 동영상 일시정지 (Start 버튼 클릭하면 재개)
         }
        binding.btnStop.setOnClickListener { binding.videoView.pause()
            binding.videoView.stopPlayback() // 동영상 정지 (Resume 버튼 클릭하면 새로 실행)
         }
    }


    }
