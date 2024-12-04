package com.example.learn.datasource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/" // Example URL

    val mealsAPI: MealsAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealsAPI::class.java)
    }
}