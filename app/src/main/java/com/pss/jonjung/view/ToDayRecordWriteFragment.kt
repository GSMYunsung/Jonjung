package com.pss.jonjung.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.TodayPost
import com.pss.jonjung.databinding.FragmentToDayRecordBinding
import com.pss.jonjung.databinding.FragmentToDayRecordWriteBinding
import com.pss.jonjung.view.adapter.ToDayRecordAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDayRecordWriteFragment : BaseFragment<FragmentToDayRecordWriteBinding>(R.layout.fragment_to_day_record_write) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.backImageView.setOnClickListener {
            this.findNavController().popBackStack()
        }

        binding.nextButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.titleTextview.text) && !TextUtils.isEmpty(binding.contentTextview.text)) {
                mainViewModel.setToday(
                    TodayPost(
                        binding.titleTextview.text.toString(),
                        binding.contentTextview.text.toString(),
                        System.currentTimeMillis().toString(),
                        mainViewModel.star.value.toString()
                    )
                )

                requireView().findNavController().navigate(R.id.action_toDayRecordWriteFragment_to_mainFragment)

            }
            else{
                Toast.makeText(requireContext(),"제목 , 내용 둘중 하나의 빈칸을 모두 채워주세요!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}