package com.example.spacenews

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{

    @GET(value = "articles?_limit=50")
    fun getArticles(): Call<List<ArticleItem>>
}