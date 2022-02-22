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
import com.pss.jonjung.data.db.entity.VideoPost
import kotlinx.coroutines.launch


@HiltViewModel
class PostViewModel @Inject constructor(
    val postRepository: PostRepository
) : ViewModel() {

    lateinit var postList: ArrayList<Post>
    lateinit var todayPostList : ArrayList<TodayPost>
    lateinit var videoPostList : ArrayList<VideoPost>

    val notPost: LiveData<Boolean> get() = _notPost
    private val _notPost = MutableLiveData<Boolean>()

    val notRecoard : LiveData<Boolean> get() = _notRecoard
    private val _notRecoard = MutableLiveData<Boolean>()

    val onsetvideo : LiveData<Boolean> get() = _onsetvideo
    private val _onsetvideo = MutableLiveData<Boolean>()

    val notVideo : LiveData<Boolean> get() = _notVideo
    private val _notVideo = MutableLiveData<Boolean>()

    val eventGetPost: LiveData<Boolean> get() = _eventGetPost
    private val _eventGetPost = MutableLiveData<Boolean>()

    val eventGetVideoPost: LiveData<Boolean> get() = _eventGetVideoPost
    private val _eventGetVideoPost = MutableLiveData<Boolean>()

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

    val videoUri: LiveData<Uri> get() = _videoUri
    private val _videoUri = MutableLiveData<Uri>()

    init {
        _star.value = "5"
        _selectText.value = "notice board"
        _isImageTrue.value = false
        _notPost.value = true
        _notVideo.value = true
        _notRecoard.value = true
        _videoUri.value = Uri.parse("")
    }

    fun isImageTrue(){
        _isImageTrue.value = true
    }

    fun setStarNum(num : String){
        _star.value = num
    }

    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            postList = arrayListOf<Post>()
            for (item in it.documents) {
                item.toObject(Post::class.java).let {
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
                item.toObject(TodayPost::class.java).let {
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

    fun setVideoUpload(filename: String, file: Uri){
        postRepository.setPVideo(filename).putFile(file).addOnSuccessListener {
            Log.d("okok","upload video ok")
        }
    }

    fun setPhoto(file : Uri,filename : String){
        postRepository.setPhoto(filename).putFile(file).addOnSuccessListener {
            Log.d("okok","upload ok")
        }
    }

    fun getVideoPost() = postRepository.getVideoPost()
        .addOnSuccessListener {
            videoPostList = arrayListOf<VideoPost>()
            for (item in it.documents) {
                item.toObject(com.pss.jonjung.data.db.entity.VideoPost::class.java).let {
//                    android.util.Log.d("로그", "postrepository : $it")
                    videoPostList.add(it!!)
                    _eventGetVideoPost.value = true
                    _notVideo.value = false
                }
            }
            if (videoPostList.size < 1)
            {
                _notVideo.value = true
            }
        }
        .addOnFailureListener {
            android.util.Log.d("로그", "error!")
        }

    fun setVideoPost(post: VideoPost) = postRepository.setVideoPost(post)
        .addOnSuccessListener {
            Log.d("okok","is video okay")
            _eventGetVideoPost.value = true
        }
        .addOnFailureListener {
            Log.d("bad","is bad")
        }

    fun getVideo(vidio : String) = viewModelScope.launch {
        postRepository.getPVideo(vidio).downloadUrl.addOnSuccessListener {  uri ->

            Log.d("urddddi",uri.toString())

            _onsetvideo.value = true
            _videoUri.value = uri

        }.addOnFailureListener {
            Log.d("urddddi","error")
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