package com.example.learn.datasource.api

import com.xita.dailyhelper.models.CategoriesResponse

class Repository {

    suspend fun getMeals(): CategoriesResponse {
        return RetrofitInstance.mealsAPI.getCategories()
    }
}