package com.example.newsapp.presentation.features.tablet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.common.ErrorType
import com.example.common.LightWeightException
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.components.errorCallback
import com.example.newsapp.presentation.features.details.DetailsScreen
import com.example.newsapp.presentation.features.news.NewsViewModel
import com.example.newsapp.presentation.features.news.components.LazyNews
import com.example.newsapp.presentation.features.news.components.LazyQueries
import com.example.newsapp.presentation.features.tablet.components.NoSelectedArticles


const val leftFraction = 0.3f
const val rightFraction = 0.6f

@Composable
fun TabletScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val articles = newsViewModel.dataFLow.collectAsLazyPagingItems()

    var selectedArticleIndex by rememberSaveable {
        mutableStateOf(-1)
    }

    var selectedArticleUrl by rememberSaveable<MutableState<String?>> {
        mutableStateOf(null)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyQueries(modifier = Modifier.padding(vertical = 7.dp)) { topic ->
            newsViewModel.fetchData(topic)
            articles.refresh()
            selectedArticleIndex = -1
        }

        Row(Modifier.fillMaxSize()) {

            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(leftFraction),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                if (articles.loadState.refresh is LoadState.Error) {
                    var errorType = remember { ErrorType.EmptyResult }


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
                    LazyNews(
                        selectedArticleIndex = selectedArticleIndex,
                        articles = articles
                    ){ articleUrl, index ->
                        selectedArticleUrl = articleUrl
                        selectedArticleIndex = index
                    }
            }

            if (selectedArticleUrl != null)
                DetailsScreen(articleUrl = selectedArticleUrl)
            else
                NoSelectedArticles()
        }
    }
}
