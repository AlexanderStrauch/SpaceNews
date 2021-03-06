package com.example.spacenews.apimodels

data class BlogItem(
    val events: List<Any>,
    val id: Int,
    val imageUrl: String,
    val launches: List<Any>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)