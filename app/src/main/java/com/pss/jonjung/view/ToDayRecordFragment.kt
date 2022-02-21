package com.pss.jonjung.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.databinding.FragmentNoticeBoardBinding
import com.pss.jonjung.databinding.FragmentToDayRecordBinding
import com.pss.jonjung.view.adapter.MainViewPagerAdapter
import com.pss.jonjung.view.adapter.ToDayRecordAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDayRecordFragment : BaseFragment<FragmentToDayRecordBinding>(R.layout.fragment_to_day_record) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        mainViewModel.notRecoard.observe(this,{

            if(mainViewModel.notRecoard.value == true){
                binding.imageView4.visibility = View.VISIBLE
                binding.textview.visibility = View.VISIBLE
            }
            else{
                binding.imageView4.visibility = View.GONE
                binding.textview.visibility = View.GONE
            }

        })

        mainViewModel.getToday()

        mainViewModel.eventGetTodayPost.observe(this, {

            binding.recyclerView.adapter = ToDayRecordAdapter((mainViewModel.todayPostList),this) // 어댑터 생성
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        } )

    }
}
