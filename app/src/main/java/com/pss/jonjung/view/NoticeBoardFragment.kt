package com.pss.jonjung.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentMainBinding
import com.pss.jonjung.databinding.FragmentNoticeBoardBinding
import com.pss.jonjung.view.adapter.CheeringAdapter
import com.pss.jonjung.view.adapter.MainViewPagerAdapter
import com.pss.jonjung.view.adapter.NoticeBoardAdapter
import com.pss.jonjung.view.adapter.ToDayRecordAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeBoardFragment : BaseFragment<FragmentNoticeBoardBinding>(R.layout.fragment_notice_board) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        mainViewModel.getPost()

        mainViewModel.notPost.observe(this,{

            if(mainViewModel.notPost.value == true){
                binding.imageView4.visibility = View.VISIBLE
                binding.textview.visibility = View.VISIBLE
            }
            else{
                binding.imageView4.visibility = View.GONE
                binding.textview.visibility = View.GONE
            }

        })

        mainViewModel.listAndPage.observe(this,{

            when(mainViewModel.listAndPage.value) {
                "list" -> {
                    binding.mainViewpager.visibility = View.GONE
                    binding.noticeBoardRecyclerview.visibility = View.VISIBLE


                }
                "page" -> {
                    binding.mainViewpager.visibility = View.VISIBLE
                    binding.noticeBoardRecyclerview.visibility = View.GONE
                }
            }

        })


        mainViewModel.eventGetPost.observe(this, {

            binding.noticeBoardRecyclerview.adapter = NoticeBoardAdapter((mainViewModel.postList),this,mainViewModel) // 어댑터 생성
           binding.noticeBoardRecyclerview.layoutManager = LinearLayoutManager(requireContext())

         binding.mainViewpager.adapter = MainViewPagerAdapter((mainViewModel.postList),this,mainViewModel) // 어댑터 생성
         binding.mainViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        } )
    }
}