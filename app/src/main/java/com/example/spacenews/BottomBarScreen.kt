package com.example.spacenews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Article: BottomBarScreen(
        route = "article",
        title = "Article",
        icon = Icons.Default.Home
    )
    object Blog: BottomBarScreen(
        route = "blog",
        title = "Blog",
        icon = Icons.Default.Menu
    )
    object Info: BottomBarScreen(
        route = "info",
        title = "Info",
        icon = Icons.Default.List
    )
    object Report: BottomBarScreen(
        route = "report",
        title = "Report",
        icon = Icons.Default.Place
    )
}
