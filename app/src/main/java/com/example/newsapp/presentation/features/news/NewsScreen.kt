package com.example.newsapp.presentation.features.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.LightWeightException
import com.example.newsapp.data.utils.ErrorType
import com.example.newsapp.data.utils.errorCallback
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.features.news.components.LazyNews
import com.example.newsapp.presentation.features.news.components.LazyQueries


@Composable
fun NewsScreen(
    fraction: Float = 1.0f,
    onNewsCardClick: (String, Int) -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val articles = newsViewModel.dataFLow.collectAsLazyPagingItems()

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyQueries { topic ->
            newsViewModel.fetchData(topic)
            articles.refresh()
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (articles.loadState.refresh is LoadState.Error) {
            var errorType by remember {
                mutableStateOf(ErrorType.EmptyResult)
            }

            LaunchedEffect(key1 = true){
                val e = (articles.loadState.refresh as LoadState.Error).error as LightWeightException
                errorType = e.errorType
                context.errorCallback(e.code)
            }
            ErrorOccurred(errorType = errorType) {
                newsViewModel.fetchData()
                articles.refresh()
            }
        }
        else
            LazyNews(articles = articles, onNewsCardClick = onNewsCardClick)
    }
}