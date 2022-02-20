package com.pss.jonjung.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseActivity
import com.pss.jonjung.base.BaseFragment
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.databinding.ActivityMainBinding
import com.pss.jonjung.databinding.FragmentBoardWriteBinding
import com.pss.jonjung.view.adapter.MainViewPagerAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.jar.Manifest


@AndroidEntryPoint
class BoardWriteFragment : BaseFragment<FragmentBoardWriteBinding>(R.layout.fragment_board_write) {

    var pickImageFromAlbum = 0

    var uriPhoto : Uri? = null

    private val postViewModel by activityViewModels<PostViewModel>()

    override fun init() {

        binding.imageView.setOnClickListener { this@BoardWriteFragment.findNavController().popBackStack() }

        binding.uploadButton.setOnClickListener {
            if(!TextUtils.isEmpty(binding.titleEditText.text) && !TextUtils.isEmpty(binding.contentEdittext.text) )
            {
                postViewModel.setPost(Post(binding.titleEditText.text.toString(),binding.contentEdittext.text.toString(),postViewModel.isImageTrue.value!!,System.currentTimeMillis().toString()))
                this@BoardWriteFragment.findNavController().popBackStack()

                if(ContextCompat.checkSelfPermission(requireView().context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                    funImageUpload(requireView())
                }

            }
            else Toast.makeText(requireContext(),"제목 , 내용 둘중 하나의 빈칸을 모두 채워주세요!", Toast.LENGTH_SHORT).show();
        }

        binding.imageUploadButton.setOnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum) }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickImageFromAlbum){
            if(resultCode == Activity.RESULT_OK){
                uriPhoto = data?.data

                postViewModel.isImageTrue()

                binding.postImage.setImageURI(uriPhoto)
            }
        }

    }

    private fun funImageUpload(view : View){

        var imgFileName = binding.titleEditText.text.toString() + "_.png"

        uriPhoto?.let { postViewModel.setPhoto(it,imgFileName) }


    }
}