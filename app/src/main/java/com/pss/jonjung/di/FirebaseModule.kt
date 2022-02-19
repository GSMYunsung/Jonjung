package com.pss.jonjung.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

   @dagger.Provides
    @javax.inject.Singleton
    fun provideFirebaseRTDB() = FirebaseDatabase.getInstance()

    @dagger.Provides
    @javax.inject.Singleton
    fun provideFirebaseStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}