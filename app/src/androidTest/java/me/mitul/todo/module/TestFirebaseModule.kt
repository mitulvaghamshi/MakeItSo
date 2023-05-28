package me.mitul.todo.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.mitul.todo.service.module.FirebaseModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [FirebaseModule::class])
object TestFirebaseModule {
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore
}
