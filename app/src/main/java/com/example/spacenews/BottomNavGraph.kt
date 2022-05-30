package com.example.spacenews

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacenews.screens.ArticleScreen
import com.example.spacenews.screens.BlogScreen
import com.example.spacenews.screens.InfoScreen
import com.example.spacenews.screens.ReportScreen

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
        composable(route = BottomBarScreen.Info.route){
            InfoScreen()
        }
        composable(route = BottomBarScreen.Report.route){
            ReportScreen()
        }
    }
}