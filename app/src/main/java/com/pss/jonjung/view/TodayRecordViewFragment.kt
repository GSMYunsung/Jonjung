package com.pss.jonjung.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentToDayRecordBinding
import com.pss.jonjung.databinding.FragmentTodayRecordViewBinding
import com.pss.jonjung.view.adapter.ToDayRecordAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodayRecordViewFragment : BaseFragment<FragmentTodayRecordViewBinding>(R.layout.fragment_today_record_view) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        mainViewModel.recordTitle.observe(this,{
           binding.titleTextview2.text = mainViewModel.recordTitle.value
            binding.contentTextview2.text = mainViewModel.recordContext.value
        })

        binding.textView9.setOnClickListener {
            mainViewModel.todayName.observe(this,{
                mainViewModel.deleteToday(mainViewModel.todayName.value!!)
                requireView().findNavController().navigate(R.id.action_todayRecordViewFragment_to_mainFragment)
            }) }

        binding.textview8.setOnClickListener {
            mainViewModel.todayre("old")
            requireView().findNavController().navigate(R.id.action_todayRecordViewFragment_to_toDayRecoardStarFragment)
        }



        mainViewModel.clickWather.observe(this,{

            when(mainViewModel.clickWather.value)
            {

                "sun" -> {
                    binding.watherImageview2.setImageResource(R.drawable.sun)
                    binding.watherTextview.text = mainViewModel.clickWather.value
                }
                "cloud" -> {
                    binding.watherImageview2.setImageResource(R.drawable.cloud)
                    binding.watherTextview.text = mainViewModel.clickWather.value

                }
                "rain" -> {
                    binding.watherImageview2.setImageResource(R.drawable.rain)
                    binding.watherTextview.text = mainViewModel.clickWather.value
                }

            }


        })

        binding.backImageView.setOnClickListener { this.findNavController().navigate(R.id.action_todayRecordViewFragment_to_mainFragment) }

    }

}
