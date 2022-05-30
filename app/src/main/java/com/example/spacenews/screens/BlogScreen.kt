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
import com.example.spacenews.BASE_URL
import com.example.spacenews.BlogList
import com.example.spacenews.apimodels.ApiInterface
import com.example.spacenews.apimodels.BlogItem
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
fun BlogScreen() {
    SpaceNewsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue),
            //color = MaterialTheme.colors.background
        ) {
            BlogScreenContent()
        }
    }
}
var blogIndex: Int = 0
const val blogLimit: Int = 20

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogScreenContent() {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Space News") }) })
    {
        runBlocking {
            launch(context = Dispatchers.IO) { getBlogs() }
        }
        BlogList(
            listItems = blogItems,
            onLoadMore = {
                blogIndex += blogLimit
            getArticles()
        })
    }
}

var blogItems: SnapshotStateList<BlogItem> = mutableStateListOf<BlogItem>()

fun getBlogs() {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData = retrofitBuilder.getBlogs(limit = blogLimit.toString(), index = blogIndex.toString())

    retrofitData.enqueue(object : Callback<List<BlogItem>?> {

        override fun onResponse(
            call: Call<List<BlogItem>?>,
            response: Response<List<BlogItem>?>
        ) {
            val responseBody = response.body()!!

            for (myBlog in responseBody) {
                blogItems.add(
                    element = BlogItem(
                        id = myBlog.id,
                        title = myBlog.title,
                        url = myBlog.url,
                        imageUrl = myBlog.imageUrl,
                        newsSite = myBlog.newsSite,
                        summary = myBlog.summary,
                        publishedAt = myBlog.publishedAt,
                        events = myBlog.events,
                        launches = myBlog.launches,
                        updatedAt = myBlog.updatedAt
                    )
                )
            }
        }

        override fun onFailure(call: Call<List<BlogItem>?>, t: Throwable) {
            Log.i("onFailure", "index=" + 1)
        }
    })
}