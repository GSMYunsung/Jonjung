package com.pss.jonjung.repository

import android.net.Uri
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.pss.jonjung.data.db.entity.Post
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val firebaseDatabase: com.google.firebase.database.FirebaseDatabase,
    private val firestore: com.google.firebase.firestore.FirebaseFirestore,
    private val firestorege : FirebaseStorage
) {
    fun getPost() = firestore.collection("pretendard_medium").orderBy("time", Query.Direction.DESCENDING).get()
    fun setPost(post : Post) = firestore.collection("pretendard_medium").document().set(post)
    fun setPhoto(name : String) =  firestorege.reference.child("images")?.child(name)
    fun getPhoto(name : String) =  firestorege.reference.child("images/$name" + "_.png")
}