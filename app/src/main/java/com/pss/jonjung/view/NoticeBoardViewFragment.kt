package com.pss.jonjung.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.databinding.FragmentCheeringBinding
import com.pss.jonjung.databinding.FragmentNoticeBoardViewBinding
import com.pss.jonjung.databinding.FragmentTodayRecordViewBinding
import com.pss.jonjung.view.adapter.CheeringAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeBoardViewFragment : BaseFragment<FragmentNoticeBoardViewBinding>(R.layout.fragment_notice_board_view) {

    private val mainViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.backImageView.setOnClickListener { this.findNavController().popBackStack()  }

            binding.titleTextview2.text = mainViewModel.recordTitle.value
            binding.contentTextview2.text = mainViewModel.recordContext.value
            binding.dateTextview.text = mainViewModel.boardListDate.value

            if(mainViewModel.photois.value == true)
            {
                mainViewModel.getPhoto(mainViewModel.recordTitle.value.toString())

                Glide.with(this)
                    .load(mainViewModel.photoUri.value)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                    .into(binding.imageView5)
            }
            else
            {
                binding.imageView5.setImageResource(R.drawable.cople)
            }

    }


}
