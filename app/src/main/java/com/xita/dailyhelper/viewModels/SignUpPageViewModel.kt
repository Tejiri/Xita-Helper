package com.xita.dailyhelper.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.xita.dailyhelper.services.FirebaseServices

class SignUpPageViewModel : ViewModel() {


    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var repeatPassword by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        repeatPassword = newRepeatPassword
    }

    fun submitForm() {
        isLoading = true
        if (email.isEmpty() || password.isEmpty()) {
            isLoading = false
            error = true
            errorMessage = "Email or Password cannot be empty"
        } else {
            FirebaseServices().createUser(
                name,
                email,
                password,
                repeatPassword
            ) { isSuccess, message ->
                if (isSuccess) {
                    isLoading = false
                    error = false
                } else {
                    isLoading = false
                    error = true
                    errorMessage = message
                }
            }
        }


    }

}