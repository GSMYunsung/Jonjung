package com.pss.jonjung.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSplashRepository(
        firebaseDatabase: com.google.firebase.database.FirebaseDatabase,
        firestore: com.google.firebase.firestore.FirebaseFirestore
    ) = com.pss.jonjung.repository.PostRepository(firebaseDatabase, firestore)
}