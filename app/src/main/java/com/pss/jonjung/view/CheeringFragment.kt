package com.pss.jonjung.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentCheeringBinding
import com.pss.jonjung.databinding.FragmentNoticeBoardBinding
import com.pss.jonjung.view.adapter.CheeringAdapter
import com.pss.jonjung.view.adapter.ToDayRecordAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheeringFragment : BaseFragment<FragmentCheeringBinding>(R.layout.fragment_cheering) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        mainViewModel.getVideoPost()

        mainViewModel.eventGetVideoPost.observe(this, {

            binding.cheeringRecyclerView.adapter = CheeringAdapter((mainViewModel.videoPostList),this,mainViewModel) // 어댑터 생성
            binding.cheeringRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } )

        mainViewModel.notVideo.observe(this,{

            Log.d("mainview",mainViewModel.notVideo.value.toString())

            if(mainViewModel.notVideo.value == true){
                binding.imageView4.visibility = View.VISIBLE
                binding.textview.visibility = View.VISIBLE
            }
            else{
                binding.imageView4.visibility = View.GONE
                binding.textview.visibility = View.GONE
            }

        })


    }


    }
