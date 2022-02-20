package com.pss.jonjung.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.repository.PostRepository
import com.pss.jonjung.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.bumptech.glide.Glide

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener




@HiltViewModel
class PostViewModel @Inject constructor(
    val postRepository: PostRepository
) : ViewModel() {

    lateinit var postList: ArrayList<com.pss.jonjung.data.db.entity.Post>

    val eventGetPost: LiveData<Boolean> get() = _eventGetPost
    private val _eventGetPost = MutableLiveData<Boolean>()

    val isImageTrue: LiveData<Boolean> get() = _isImageTrue
    private val _isImageTrue = MutableLiveData<Boolean>()

    val photoUri: LiveData<Uri> get() = _photoUri
    private val _photoUri = MutableLiveData<Uri>()

    init {
        _isImageTrue.value = false
    }

    fun isImageTrue(){
        _isImageTrue.value = true
    }



    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            postList = arrayListOf<com.pss.jonjung.data.db.entity.Post>()
            for (item in it.documents) {
                item.toObject(com.pss.jonjung.data.db.entity.Post::class.java).let {
//                    android.util.Log.d("로그", "postrepository : $it")
                    postList.add(it!!)
                    _eventGetPost.value = true
                }
            }
        }
        .addOnFailureListener {
            android.util.Log.d("로그", "error!")
        }

    fun setPost(post: Post) = postRepository.setPost(post)
        .addOnSuccessListener {
            Log.d("okok","is okay")
            _eventGetPost.value = true
        }
        .addOnFailureListener {
            Log.d("bad","is bad")
        }

    fun setPhoto(file : Uri,filename : String){
        postRepository.setPhoto(filename).putFile(file).addOnSuccessListener {
            Log.d("okok","upload ok")
        }
    }

    fun getPhoto(filename : String){
        postRepository.getPhoto(filename).downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시

            _photoUri.value = uri

        }.addOnFailureListener { //이미지 로드 실패시
            Toast.makeText(
                getApplicationContext<Context>(),
                "실패",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}