package com.xita.dailyhelper.services

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage

class FirebaseServices {

    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = Firebase.firestore
    private val storage = Firebase.storage

    fun addRecipe(uri: Uri?){
        if (uri != null) {
            storage.reference.child("images/image.jpg").putFile(uri).addOnCompleteListener {result->
                if (result.isSuccessful){
    //                result.result.
                }else{
                    Log.i("MYINFO",result.exception?.message.toString())
                }

            }
        }


    }

    fun checkUserLoggedIn(): FirebaseUser? {
        return auth.currentUser
    }

    fun createUser(
        name: String,
        email: String,
        password: String,
        repeatPassword: String,
        onResult: (isSuccess: Boolean, message: String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->


            if (task.isSuccessful) {
                val user2 = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "password" to password,
                    "repeatPassword" to repeatPassword
                )
                db.collection("users")
                    .add(user2)
                val user = auth.currentUser

                onResult(true, "Login Successful")
//                updateUI(user)
            } else {

                onResult(false, task.exception?.message.toString())
            }
        }

    }

    fun logUserIn(
        email: String,
        password: String,
        onResult: (isSuccess: Boolean, message: String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->


            if (task.isSuccessful) {
                onResult(true, "Login Successful")
            } else {
                onResult(false, task.exception?.message.toString())
            }

        }
    }

    fun logUserOut(){
        auth.signOut()
    }
}