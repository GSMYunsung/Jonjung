package com.pss.jonjung.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.databinding.FragmentCheeringBinding
import com.pss.jonjung.databinding.FragmentNoticeBoardModifyBinding
import com.pss.jonjung.view.adapter.CheeringAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate


@AndroidEntryPoint
class NoticeBoardModifyFragment : BaseFragment<FragmentNoticeBoardModifyBinding>(R.layout.fragment_notice_board_modify) {

    var pickImageFromAlbum = 0

    private val mainViewModel by activityViewModels<PostViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun init() {

        binding.titleEditText.setText(mainViewModel.recordTitle.value)
        binding.contentEdittext.setText(mainViewModel.recordContext.value)

        if(mainViewModel.photois.value == true)
        {
            mainViewModel.getPhoto(mainViewModel.recordTitle.value.toString())

            Glide.with(this)
                .load(mainViewModel.photoUri.value)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                .into(binding.postImage)
        }
        else
        {
            binding.postImage.setImageResource(R.drawable.cople)
        }

        binding.uploadButton.setOnClickListener {

                if(!TextUtils.isEmpty(binding.titleEditText.text) && !TextUtils.isEmpty(binding.contentEdittext.text) )
                {

                    Log.d("coocococo","coocococo")
                         mainViewModel.deletePost(mainViewModel.remember.value!!)

                        mainViewModel.setPost(Post(binding.titleEditText.text.toString(),binding.contentEdittext.text.toString(),true,System.currentTimeMillis().toString(),
                        LocalDate.now().toString()))

                        mainViewModel.setListAndPageAndPicture(binding.titleEditText.text.toString(),binding.contentEdittext.text.toString(),mainViewModel.boardListDate.value.toString(),true)

                        mainViewModel.deletePhoto(mainViewModel.remember.value!!)

                        if(ContextCompat.checkSelfPermission(requireView().context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED){
                            funImageUpload(requireView())
                        }

                            this.findNavController().navigate(R.id.action_noticeBoardModifyFragment_to_noticeBoardViewFragment)


                }
                else Toast.makeText(requireContext(),"제목 , 내용 둘중 하나의 빈칸을 모두 채워주세요!", Toast.LENGTH_SHORT).show()
        }

        binding.backImageView.setOnClickListener { this.findNavController().navigate(R.id.action_noticeBoardModifyFragment_to_noticeBoardViewFragment) }

        binding.imageUploadButton.setOnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum) }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickImageFromAlbum){
            if(resultCode == Activity.RESULT_OK){
                mainViewModel.BoardListUri(data?.data!!)

                mainViewModel.isImageTrue()

                mainViewModel.photoChange()

                binding.postImage.setImageURI(mainViewModel.BoardListUri.value)
            }
        }

    }

    private fun funImageUpload(view : View){

        var imgFileName = binding.titleEditText.text.toString() + "_.png"

        Log.d("imgFileName",imgFileName)

        mainViewModel.BoardListUri.observe(this,{ it->
            mainViewModel.setPhoto(it,imgFileName)
        })


    }

    }