package com.example.spacenews

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.distinctUntilChanged
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spacenews.apimodels.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleList(
    listItems: List<ArticleItem>,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        items(listItems) { item ->
            ArticleRow(article = item)
        }
    }

    InfiniteList(listState = listState) {
        onLoadMore()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogList(
    listItems: List<BlogItem>,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        items(listItems) { item ->
            BlogRow(blog = item)
        }
    }

    InfiniteList(listState = listState) {
        onLoadMore()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportList(
    listItems: List<ReportItem>,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        items(listItems) { item ->
            ReportRow(report = item)
        }
    }

    InfiniteList(listState = listState) {
        onLoadMore()
    }
}


@Composable
fun InfiniteList(
    listState: LazyListState,
    buffer: Int = 5,
    onExtend: () -> Unit
) {
    val extend = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(extend) {
        snapshotFlow { extend.value }
            .distinctUntilChanged()
            .collect {
                onExtend()
            }
    }
}