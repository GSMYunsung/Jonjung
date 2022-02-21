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
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import com.bumptech.glide.Glide

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener
import com.pss.jonjung.data.db.entity.TodayPost
import kotlinx.coroutines.launch


@HiltViewModel
class PostViewModel @Inject constructor(
    val postRepository: PostRepository
) : ViewModel() {

    lateinit var postList: ArrayList<com.pss.jonjung.data.db.entity.Post>
    lateinit var todayPostList : ArrayList<TodayPost>

    val notPost: LiveData<Boolean> get() = _notPost
    private val _notPost = MutableLiveData<Boolean>()

    val notRecoard : LiveData<Boolean> get() = _notRecoard
    private val _notRecoard = MutableLiveData<Boolean>()

    val eventGetPost: LiveData<Boolean> get() = _eventGetPost
    private val _eventGetPost = MutableLiveData<Boolean>()

    val eventGetTodayPost: LiveData<Boolean> get() = _eventGetTodayPost
    private val _eventGetTodayPost = MutableLiveData<Boolean>()

    val selectText: LiveData<String> get() = _selectText
    private val _selectText = MutableLiveData<String>()

    val star: LiveData<String> get() = _star
    private val _star = MutableLiveData<String>()

    val isImageTrue: LiveData<Boolean> get() = _isImageTrue
    private val _isImageTrue = MutableLiveData<Boolean>()

    val photoUri: LiveData<Uri> get() = _photoUri
    private val _photoUri = MutableLiveData<Uri>()

    init {
        _star.value = "0"
        _selectText.value = "notice board"
        _isImageTrue.value = false
        _notPost.value = true
    }

    fun isImageTrue(){
        _isImageTrue.value = true
    }

    fun setStarNum(num : String){
        _star.value = num
    }

    fun setSelectText(text : String){
        _selectText.value = text
    }


    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            postList = arrayListOf<com.pss.jonjung.data.db.entity.Post>()
            for (item in it.documents) {
                item.toObject(com.pss.jonjung.data.db.entity.Post::class.java).let {
                    postList.add(it!!)
                    _eventGetPost.value = true
                    _notPost.value = false
                }
            }
            if (postList.size < 1)
            {
                _notPost.value = true
            }
        }
        .addOnFailureListener {
            android.util.Log.d("로그", "error!")
        }

    fun setToday(post : TodayPost) = postRepository.setToday(post)
        .addOnCanceledListener {
            _eventGetTodayPost.value = true
        }
        .addOnFailureListener {
            Log.d("bad","is bad")
        }

    fun getToday() = postRepository.getToday()
        .addOnSuccessListener {
            todayPostList = arrayListOf<TodayPost>()
            for (item in it.documents) {
                item.toObject(com.pss.jonjung.data.db.entity.TodayPost::class.java).let {
//                    android.util.Log.d("로그", "postrepository : $it")
                    todayPostList.add(it!!)
                    _eventGetTodayPost.value = true
                    _notRecoard.value = false
                }
            }
            if (todayPostList.size < 1)
            {
                _notRecoard.value = true
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

    fun getPhoto(filename : String)  = viewModelScope.launch {
        postRepository.getPhoto(filename).downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시

            Log.d("uri",uri.toString())

            _photoUri.value = uri

        }.addOnFailureListener {
            //이미지 로드 실패시

        }
    }



}