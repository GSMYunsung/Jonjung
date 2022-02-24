package com.pss.jonjung.view

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.databinding.FragmentBoardWriteBinding
import com.pss.jonjung.databinding.FragmentMainBinding
import com.pss.jonjung.view.adapter.MainViewPagerAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import com.squareup.okhttp.Dispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

            mainViewModel.getPost()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,NoticeBoardFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        binding.noticeBoardTextview.setOnClickListener {

            notice()

            binding.setListAndPageContent.visibility = View.VISIBLE
        }

        binding.cheeringVideoTextview.setOnClickListener {

            cheering()

            binding.setListAndPageContent.visibility = View.GONE
        }

        binding.todayRecordTextview.setOnClickListener {

            record()

            binding.setListAndPageContent.visibility = View.GONE

        }

        binding.listImageview.setOnClickListener {
            binding.listImageview.setBackgroundResource(R.drawable.wather_background)
            binding.pageImageview.setBackgroundResource(R.drawable.main_button)
            mainViewModel.listAndPage("list")
        }

        binding.pageImageview.setOnClickListener {
            binding.pageImageview.setBackgroundResource(R.drawable.wather_background)
            binding.listImageview.setBackgroundResource(R.drawable.main_button)
            mainViewModel.listAndPage("page")
        }

        binding.adsf.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_mainFragment_to_boardWriteFragment)
        }

        binding.imageView2.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_mainFragment_to_toDayRecoardStarFragment)
        }

        binding.imageview2Textveiw.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_mainFragment_to_toDayRecoardStarFragment)
        }

        binding.videoImageview.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_mainFragment_to_videoFragment)
        }


    }

    private fun record() {

        binding.noticeBoardTextview.setTextColor(Color.parseColor("#dbdbdb"))

        binding.cheeringVideoTextview.setTextColor(Color.parseColor("#dbdbdb"))

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,ToDayRecordFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        binding.todayRecordTextview.setTextColor(Color.BLACK)

        binding.imageView2.visibility = View.VISIBLE
        binding.imageview2Textveiw.visibility = View.VISIBLE

        visNotice()
        visVideo()


    }

    private fun cheering() {
        binding.noticeBoardTextview.setTextColor(Color.parseColor("#dbdbdb"))

        binding.todayRecordTextview.setTextColor(Color.parseColor("#dbdbdb"))

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,CheeringFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        visRecord()
        visNotice()

        binding.videoImageview.visibility = View.VISIBLE
        binding.videoTextview.visibility = View.VISIBLE

        binding.cheeringVideoTextview.setTextColor(Color.BLACK)

    }

    private fun notice(){
        binding.noticeBoardTextview.setTextColor(Color.BLACK)

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,NoticeBoardFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        visRecord()
        visVideo()

        binding.adsf.visibility = View.VISIBLE
        binding.adsfTextview.visibility = View.VISIBLE

        binding.cheeringVideoTextview.setTextColor(Color.parseColor("#dbdbdb"))

        binding.todayRecordTextview.setTextColor(Color.parseColor("#dbdbdb"))
    }

    private fun visNotice(){
        binding.adsf.visibility = View.GONE
        binding.adsfTextview.visibility = View.GONE
    }

    private fun visRecord(){
        binding.imageView2.visibility = View.GONE
        binding.imageview2Textveiw.visibility = View.GONE
    }

    private fun visVideo(){
        binding.videoImageview.visibility = View.GONE
        binding.videoTextview.visibility = View.GONE
    }
}