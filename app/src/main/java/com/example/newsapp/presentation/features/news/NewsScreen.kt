package com.example.newsapp.presentation.features.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.utils.ErrorType
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.features.news.components.LazyNews
import com.example.newsapp.presentation.features.news.components.LazyQueries


@Composable
fun NewsScreen(
    fraction: Float = 1.0f,
    onNewsCardClick: (Article, Int) -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val articles = newsViewModel.dataFLow.collectAsLazyPagingItems()

    Column(
        Modifier.fillMaxHeight().fillMaxWidth(fraction),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyQueries { topic ->
            newsViewModel.fetchData(topic)
            articles.refresh()
        }


        Spacer(modifier = Modifier.height(20.dp))

        if (articles.loadState.refresh is LoadState.Error)
            ErrorOccurred(errorType = ErrorType.EmptyResult)
        else
            LazyNews(articles = articles, onNewsCardClick = onNewsCardClick)
    }
}