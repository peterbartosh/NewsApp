package com.example.newsapp.presentation.features.news.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.utils.formatDateString
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.shimmerEffect
import com.example.newsapp.presentation.theme.NeutralDark


@Composable
inline fun ArticleCard(
    modifier: Modifier = Modifier,
    isLast: Boolean = false,
    isFirst: Boolean = false,
    article: Article,
    isLoadingData: Boolean,
    crossinline onClick: () -> Unit = {}
) {

    var isError by remember {
        mutableStateOf(false)
    }

    var isReady by remember {
        mutableStateOf(false)
    }

    val shape = if (isFirst)
        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    else if (isLast)
        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
    else
        RectangleShape

    if (!isError)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 400.dp)
                .padding(horizontal = 5.dp)
                .clip(shape)
                .background(MaterialTheme.colorScheme.background)
                .clickable { if (isReady) onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
            ) {
                    val imgId =
                        if (isSystemInDarkTheme()) R.drawable.placeholder_dark else R.drawable.placeholder_light

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            //.heightIn(min = 100.dp, max = 700.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .align(Alignment.Center),
                        model = article.urlToImage,
                        placeholder = painterResource(id = imgId),
                        onError = {
                            isError = true
                        },
                        onSuccess = {
                            isReady = true
                        },
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .shimmerEffect(!isReady)
                        .align(Alignment.Center)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .background(
                            NeutralDark.copy(alpha = 0.6f),
                            RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .align(Alignment.BottomCenter)
                ) {
                    Text(
                        modifier = Modifier
                            .heightIn(min = 80.dp)
                            .widthIn(min = 250.dp)
                            .padding(horizontal = 20.dp)
                            .shimmerEffect(isLoadingData)
                            .align(Alignment.Center),
                        text = if (!isLoadingData) article.title else "",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }

            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp)
                    .padding(start = 20.dp, top = 20.dp, end = 5.dp)
                    .background(
                        if (isLoadingData)
                            MaterialTheme.colorScheme.secondaryContainer
                        else
                            MaterialTheme.colorScheme.background
                    )
                    .shimmerEffect(isLoadingData),
                text = if (!isLoadingData) article.description else "",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp), horizontalArrangement = Arrangement.End) {

                Text(
                    modifier = Modifier
                        .widthIn(min = 70.dp)
                        .wrapContentHeight()
                        .padding(end = 5.dp)
                        .background(
                            if (isLoadingData)
                                MaterialTheme.colorScheme.secondaryContainer
                            else
                                MaterialTheme.colorScheme.background
                        )
                        .shimmerEffect(isLoadingData),
                    text = if (!isLoadingData) formatDateString(article.publishedAt) else "",
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            )
        }
}
