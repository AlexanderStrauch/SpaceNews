package com.example.spacenews.apimodels

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{

    @GET(value = "articles?_limit=50")
    fun getArticles(): Call<List<ArticleItem>>

    @GET(value = "blogs?_limit=50")
    fun getBlogs(): Call<List<BlogItem>>
}
