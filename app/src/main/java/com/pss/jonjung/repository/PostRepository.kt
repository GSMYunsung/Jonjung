package com.pss.jonjung.repository

import javax.inject.Inject

class PostRepository @Inject constructor(
    private val firebaseDatabase: com.google.firebase.database.FirebaseDatabase,
    private val firestore: com.google.firebase.firestore.FirebaseFirestore
) {
    fun getPost() = firestore.collection("pretendard_medium").get()
}