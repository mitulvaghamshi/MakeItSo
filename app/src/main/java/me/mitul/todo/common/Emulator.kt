package me.mitul.todo.common

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val EMULATOR_HOST = "10.0.2.2"
private const val FIRESTORE_PORT = 8080
private const val AUTH_PORT = 9099

fun useEmulators() {
    Firebase.auth.useEmulator(EMULATOR_HOST, AUTH_PORT)
    Firebase.firestore.useEmulator(EMULATOR_HOST, FIRESTORE_PORT)
}
