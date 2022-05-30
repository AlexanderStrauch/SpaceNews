package com.example.spacenews.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.spacenews.*
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

@Composable
fun ArticleScreen() {
    SpaceNewsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            //color = MaterialTheme.colors.background
        ) {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Space News") }) })
    {
        runBlocking {
            val job: Job = launch(context = Dispatchers.IO) { getArticles() }
        }
        SimpleList(listitems = articleItems)
    }
}

const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

var articleItems: SnapshotStateList<ArticleViewModel> = mutableStateListOf<ArticleViewModel>()


private fun getArticles() {

    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)


    Log.i("getArticles", "index=" + 1)
    val retrofitData = retrofitBuilder.getArticles()

    retrofitData.enqueue(object : Callback<List<ArticleItem>?> {

        override fun onResponse(
            call: Call<List<ArticleItem>?>,
            response: Response<List<ArticleItem>?>
        ) {

            Log.i("onResponse", "index=" + 1)
            val responseBody = response.body()!!

            for (myArticle in responseBody) {
                articleItems.add(
                    element = ArticleViewModel(
                        id = myArticle.id,
                        title = myArticle.title,
                        url = myArticle.url,
                        imageUrl = myArticle.imageUrl,
                        newsSite = myArticle.newsSite,
                        summary = myArticle.summary,
                        publishedAt = myArticle.publishedAt
                    )
                )
            }
        }

        override fun onFailure(call: Call<List<ArticleItem>?>, t: Throwable) {
            Log.i("onFailure", "index=" + 1)
        }
    })


}