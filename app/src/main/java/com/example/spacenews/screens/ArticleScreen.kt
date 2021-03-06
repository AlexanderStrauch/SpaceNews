package com.example.spacenews.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.spacenews.*
import com.example.spacenews.apimodels.ApiInterface
import com.example.spacenews.apimodels.ArticleItem
import com.example.spacenews.ui.theme.SpaceNewsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleScreen() {
    SpaceNewsTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
        ) {
            ArticleScreenContent()
        }
    }
}
var articleIndex: Int = 0
const val articleLimit: Int = 20

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleScreenContent() {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Space News") }) })
    {
        runBlocking {
            launch(context = Dispatchers.IO) { getArticles() }
        }
        ArticleList(
            listItems = articleItems,
            onLoadMore = {
                articleIndex += articleLimit
                getArticles()
            })
    }
}

var articleItems: SnapshotStateList<ArticleItem> = mutableStateListOf<ArticleItem>()

fun getArticles() {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData = retrofitBuilder.getArticles(limit = articleLimit.toString(), index = articleIndex.toString())

    retrofitData.enqueue(object : Callback<List<ArticleItem>?> {

        override fun onResponse(
            call: Call<List<ArticleItem>?>,
            response: Response<List<ArticleItem>?>
        ) {
            val responseBody = response.body()!!

            for (myArticle in responseBody) {
                articleItems.add(
                    element = ArticleItem(
                        id = myArticle.id,
                        title = myArticle.title,
                        url = myArticle.url,
                        imageUrl = myArticle.imageUrl,
                        newsSite = myArticle.newsSite,
                        summary = myArticle.summary,
                        publishedAt = myArticle.publishedAt,
                        events = myArticle.events,
                        launches = myArticle.launches,
                        featured = myArticle.featured,
                        updatedAt = myArticle.updatedAt
                    )
                )
            }
        }

        override fun onFailure(call: Call<List<ArticleItem>?>, t: Throwable) {
            Log.i("onFailure", "index=" + 1)
        }
    })
}