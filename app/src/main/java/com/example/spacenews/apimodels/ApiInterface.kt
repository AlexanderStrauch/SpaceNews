package com.example.spacenews.apimodels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(value = "articles")
    fun getArticles(
        @Query("_limit") limit: String,
        @Query("_start") index: String
    ): Call<List<ArticleItem>>

    @GET(value = "blogs")
    fun getBlogs(
        @Query("_limit") limit: String,
        @Query("_start") index: String
    ): Call<List<BlogItem>>

    @GET(value = "reports")
    fun getReports(
        @Query("_limit") limit: String,
        @Query("_start") index: String
    ): Call<List<ReportItem>>
}
