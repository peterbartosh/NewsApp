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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.utils.ErrorType
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.features.details.DetailsScreen
import com.example.newsapp.presentation.features.details.DetailsViewModel
import com.example.newsapp.presentation.features.news.NewsViewModel
import com.example.newsapp.presentation.features.news.components.LazyNews
import com.example.newsapp.presentation.features.news.components.LazyQueries


const val leftFraction = 0.3f
const val rightFraction = 0.6f

@Composable
fun TabletScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    var selectedArticle by remember<MutableState<Article?>> {
        mutableStateOf(null)
    }


    var selectedArticleIndex by remember {
        mutableStateOf(-1)
    }

    val articles = newsViewModel.dataFLow.collectAsLazyPagingItems()

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

                if (articles.loadState.refresh is LoadState.Error)

                    ErrorOccurred(errorType = ErrorType.EmptyResult) {
                        newsViewModel.fetchData()
                        articles.refresh()
                    }
                else
                    LazyNews(
                        selectedArticleIndex = selectedArticleIndex,
                        articles = articles
                    ){ article, index ->
                        selectedArticle = article
                        selectedArticleIndex = index
                    }
            }


            if (selectedArticle != null)
                DetailsScreen(article = selectedArticle)
            else
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
    }
}