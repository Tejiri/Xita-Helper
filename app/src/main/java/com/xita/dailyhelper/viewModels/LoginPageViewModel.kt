package com.xita.dailyhelper.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.learn.customComponents.UIComponents
import com.xita.dailyhelper.services.FirebaseServices

class LoginPageViewModel:ViewModel() {
   var email by mutableStateOf("")
   var password by mutableStateOf("")
   var error by mutableStateOf(false)
   var errorMessage by mutableStateOf("")
   var isLoading by mutableStateOf(false)
   var logInSuccess by mutableStateOf(false)

   fun onEmailChange(newEmail: String) {
      email = newEmail
   }

   fun onPasswordChange(newPassword: String) {
      password = newPassword
   }

   fun logUserIn(){
      isLoading = true

      if (email.isEmpty() || password.isEmpty()) {
         isLoading = false
         error = true
         errorMessage = "Email or Password cannot be empty"
      } else {
         FirebaseServices().logUserIn(email,password,{ isSuccess, message ->
            if (isSuccess){
               logInSuccess = true
               isLoading = false
               error = false
            }else{
               isLoading = false
               error = true
               errorMessage = message
            }
         })
      }

   }
}