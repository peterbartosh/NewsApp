package com.example.newsapp.presentation.features.news

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.newsapp.domain.Article

const val newsRoute = "news"

fun NavGraphBuilder.newsScreen(navigateToDetails: (Article) -> Unit){
    composable(
        route = newsRoute
    ) {
        val newsViewModel = hiltViewModel<NewsViewModel>()
        NewsScreen(
            newsViewModel = newsViewModel,
            onNewsCardClick = { article, _ -> navigateToDetails(article) }
        )
    }
}