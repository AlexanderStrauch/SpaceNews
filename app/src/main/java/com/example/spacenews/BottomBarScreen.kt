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
        title = "Articles",
        icon = Icons.Default.Home
    )
    object Blog: BottomBarScreen(
        route = "blog",
        title = "Blogs",
        icon = Icons.Default.Menu
    )
    object Report: BottomBarScreen(
        route = "report",
        title = "ISS Reports",
        icon = Icons.Default.Place
    )
}
