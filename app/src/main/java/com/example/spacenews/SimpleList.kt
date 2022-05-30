package com.example.spacenews

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimpleList(listitems: List<ArticleViewModel>){
    LazyColumn{
        items(listitems){
            item -> ArticleRow(article = item)
        }

        Log.i("SimpleList", "index=" +1)
    }
}