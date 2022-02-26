package com.pss.jonjung.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentToDayRecoardStarBinding
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDayRecoardStarFragment : BaseFragment<FragmentToDayRecoardStarBinding>(R.layout.fragment_to_day_recoard_star) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.backImageView.setOnClickListener {
            this.findNavController().popBackStack()
        }

        mainViewModel.wather.observe(this,{
           when(mainViewModel.wather.value){
               "sun" -> binding.starTextview.text = "해가 쩅쨍 오늘은 반짝반짝"
               "cloud" -> binding.starTextview.text = "오늘은 구름진날 조금 어두워"
               "rain" -> binding.starTextview.text = "오늘만은 위로해줘 내마음에 비가내려"
           }
        })

        binding.sunImageview.setOnClickListener {
            binding.sunImageview.setBackgroundResource(R.drawable.wather_background)
            binding.cloudImageview.setBackgroundResource(R.drawable.main_button)
            binding.rainImageview.setBackgroundResource(R.drawable.main_button)
            binding.watherImageview.setImageResource(R.drawable.sun)

            mainViewModel.setWather("sun")
        }

        binding.cloudImageview.setOnClickListener {
            binding.cloudImageview.setBackgroundResource(R.drawable.wather_background)
            binding.sunImageview.setBackgroundResource(R.drawable.main_button)
            binding.rainImageview.setBackgroundResource(R.drawable.main_button)
            binding.watherImageview.setImageResource(R.drawable.cloud)

            mainViewModel.setWather("cloud")
        }

        binding.rainImageview.setOnClickListener {
            binding.rainImageview.setBackgroundResource(R.drawable.wather_background)
            binding.cloudImageview.setBackgroundResource(R.drawable.main_button)
            binding.sunImageview.setBackgroundResource(R.drawable.main_button)
            binding.watherImageview.setImageResource(R.drawable.rain)

            mainViewModel.setWather("rain")
        }

        binding.nextButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_toDayRecoardStarFragment_to_toDayRecordWriteFragment)
        }

    }
}