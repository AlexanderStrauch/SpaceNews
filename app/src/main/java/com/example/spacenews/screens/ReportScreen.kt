package com.example.spacenews.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.spacenews.ui.theme.SpaceNewsTheme

@Composable
fun ReportScreen() {
    SpaceNewsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
            //color = MaterialTheme.colors.background
        ) {

        }
    }
}