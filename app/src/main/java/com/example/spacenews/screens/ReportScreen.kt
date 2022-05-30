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
import com.example.spacenews.ReportList
import com.example.spacenews.apimodels.ApiInterface
import com.example.spacenews.apimodels.BlogItem
import com.example.spacenews.apimodels.ReportItem
import com.example.spacenews.ui.theme.SpaceNewsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportScreen() {
    SpaceNewsTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
        ) {
            ReportScreenContent()
        }
    }
}

var reportIndex: Int = 0
const val reportLimit: Int = 20


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportScreenContent() {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Space News") }) })
    {
        runBlocking {
            launch(context = Dispatchers.IO) { getReports() }
        }
        ReportList(listItems = reportItems,
            onLoadMore = {
                reportIndex += reportLimit
                getArticles()
            })
    }
}

var reportItems: SnapshotStateList<ReportItem> = mutableStateListOf<ReportItem>()

fun getReports() {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData = retrofitBuilder.getReports(limit = reportLimit.toString(), index = reportIndex.toString())

    retrofitData.enqueue(object : Callback<List<ReportItem>?> {

        override fun onResponse(
            call: Call<List<ReportItem>?>,
            response: Response<List<ReportItem>?>
        ) {
            val responseBody = response.body()!!

            for (myReport in responseBody) {
                reportItems.add(
                    element = ReportItem(
                        id = myReport.id,
                        title = myReport.title,
                        url = myReport.url,
                        imageUrl = myReport.imageUrl,
                        newsSite = myReport.newsSite,
                        summary = myReport.summary,
                        publishedAt = myReport.publishedAt,
                        updatedAt = myReport.updatedAt
                    )
                )
            }
        }

        override fun onFailure(call: Call<List<ReportItem>?>, t: Throwable) {
            Log.i("onFailure", "index=" + 1)
        }
    })
}