package com.example.spacenews

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.spacenews.apimodels.ArticleItem
import com.example.spacenews.apimodels.ArticleRow
import com.example.spacenews.apimodels.BlogItem
import com.example.spacenews.apimodels.BlogRow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleList(listitems: List<ArticleItem>){
    LazyColumn{
        items(listitems){
            item -> ArticleRow(article = item)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogList(listitems: List<BlogItem>){
    LazyColumn{
        items(listitems){
                item -> BlogRow(blog = item)
        }
    }
}