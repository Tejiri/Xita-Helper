package com.example.learn.datasource.api

import com.xita.dailyhelper.models.CategoriesResponse
import retrofit2.http.GET

interface MealsAPI {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

//    Call<List<Repo>> listRepos(@Path("user") String user);

}