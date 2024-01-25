package com.example.newsapp.presentation.features.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.LightWeightException
import com.example.newsapp.data.utils.ErrorType
import com.example.newsapp.data.utils.errorCallback
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.features.details.DetailsScreen
import com.example.newsapp.presentation.features.news.NewsViewModel
import com.example.newsapp.presentation.features.news.components.LazyNews
import com.example.newsapp.presentation.features.news.components.LazyQueries


const val leftFraction = 0.3f
const val rightFraction = 0.6f

@Composable
fun TabletScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    tabletViewModel: TabletViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val articles = newsViewModel.dataFLow.collectAsLazyPagingItems()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyQueries(modifier = Modifier.padding(vertical = 7.dp)) { topic ->
            newsViewModel.fetchData(topic)
            articles.refresh()
            tabletViewModel.selectedArticleIndex = -1
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
                        selectedArticleIndex = tabletViewModel.selectedArticleIndex,
                        articles = articles
                    ){ articleUrl, index ->
                        tabletViewModel.selectedArticleUrl = articleUrl
                        tabletViewModel.selectedArticleIndex = index
                    }
            }

            if (tabletViewModel.selectedArticleUrl != null)
                DetailsScreen(articleUrl = tabletViewModel.selectedArticleUrl)
            else
                NoSelectedArticles()
        }
    }
}

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