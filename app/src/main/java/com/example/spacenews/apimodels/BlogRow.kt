package com.example.spacenews.apimodels

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogRow(blog: BlogItem) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(blog.url)) }

    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    context.startActivity(intent)
                },
            shape = RoundedCornerShape(5.dp),
            elevation = 5.dp,
        ) {
            Box(
                modifier = Modifier.height(350.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(blog.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 300f
                            )
                        )
                ) {

                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, bottom = 25.dp, end = 10.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = blog.title,
                        modifier = Modifier.padding(top = 0.dp, bottom = 0.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }
            val date = LocalDate.parse(blog.publishedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(350.dp)
                    .align(alignment = Alignment.CenterHorizontally),
            ){
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 5.dp)
                        .align(alignment = Alignment.BottomStart),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp
                    )
                )
                Text(
                    text = blog.newsSite,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 5.dp)
                        .align(alignment = Alignment.BottomEnd),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp
                    )
                )
            }
        }
        if (isSystemInDarkTheme()){
            Text(
                text = blog.summary,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp,top = 5.dp, end = 5.dp, bottom = 15.dp)
                    .align(alignment = Alignment.Start),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }
        else {
            Text(
                text = blog.summary,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp,top = 5.dp, end = 5.dp, bottom = 15.dp)
                    .align(alignment = Alignment.Start),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                )
            )
        }
    }
}
