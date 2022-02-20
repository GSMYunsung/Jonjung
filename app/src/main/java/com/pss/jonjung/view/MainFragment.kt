package com.pss.jonjung.view

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

        mainViewModel.eventGetPost.observe(this, {

            binding.mainViewpager.adapter = MainViewPagerAdapter((mainViewModel.postList),this,mainViewModel) // 어댑터 생성
            binding.mainViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL})

        binding.adsf.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_mainFragment_to_boardWriteFragment)
        }

    }
}