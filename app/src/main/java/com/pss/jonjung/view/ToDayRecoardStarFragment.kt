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
import com.pss.jonjung.databinding.FragmentToDayRecoardStarBindingImpl
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDayRecoardStarFragment : BaseFragment<FragmentToDayRecoardStarBinding>(R.layout.fragment_to_day_recoard_star) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.backImageView.setOnClickListener {
            this.findNavController().popBackStack()
        }

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            mainViewModel.setStarNum(fl.toString())
            Log.d("coocpam",mainViewModel.star.value.toString())
            binding.starTextview.text = "${fl}점"
        }

        binding.nextButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_toDayRecoardStarFragment_to_toDayRecordWriteFragment)
        }

    }
}