package com.xita.dailyhelper.models


data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) {

    constructor(idCategory: String) : this(
        idCategory,
        strCategory = "",
        strCategoryThumb = "",
        strCategoryDescription = ""
    )
}

data class CategoriesResponse(val categories: List<Category>)
