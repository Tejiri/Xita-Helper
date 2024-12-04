package com.xita.dailyhelper.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learn.datasource.api.Repository
import com.xita.dailyhelper.models.Category
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {
    private var repository = Repository()
    var isLoading by mutableStateOf(true)
    var categoriesResponse = mutableStateListOf<Category>()
        private set

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            try {
                isLoading = true
                val response = repository.getMeals()
                categoriesResponse.clear()
                categoriesResponse.addAll(response.categories)
            } catch (e: Exception) {
                Log.i("MYEXCEPTION", e.localizedMessage)
            } finally {
                isLoading = false
            }

        }

    }
}