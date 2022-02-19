package com.pss.jonjung.viewmodel

import androidx.lifecycle.ViewModel
import com.pss.jonjung.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    val postRepository: PostRepository
) : ViewModel() {

    lateinit var postList: ArrayList<com.pss.jonjung.data.db.entity.Post>

    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            postList = arrayListOf<com.pss.jonjung.data.db.entity.Post>()
            for (item in it.documents) {
                item.toObject(com.pss.jonjung.data.db.entity.Post::class.java).let {
//                    android.util.Log.d("로그", "postrepository : $it")
                    postList.add(it!!)
                }
            }
        }
        .addOnFailureListener {
            android.util.Log.d("로그", "error!")
        }


}