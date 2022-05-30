package com.example.spacenews

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacenews.screens.ArticleScreen
import com.example.spacenews.screens.BlogScreen
import com.example.spacenews.screens.ReportScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Article.route
    ){
        composable(route = BottomBarScreen.Article.route){
            ArticleScreen()
        }
        composable(route = BottomBarScreen.Blog.route){
            BlogScreen()
        }
        composable(route = BottomBarScreen.Report.route){
            ReportScreen()
        }
    }
}