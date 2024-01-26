package com.example.newsapp.presentation.features.tablet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R


@Composable
fun NoSelectedArticles() {
    Surface(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 10.dp),
                painter = painterResource(id = R.drawable.error_occured),
                contentDescription = null
            )


            Text(text = "No articles have been selected", fontSize = 16.sp)
        }
    }
}