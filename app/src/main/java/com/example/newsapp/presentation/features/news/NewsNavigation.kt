package com.example.newsapp.presentation.features.news

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val newsRoute = "news"

fun NavGraphBuilder.newsScreen(navigateToDetails: (String) -> Unit){
    composable(
        route = newsRoute
    ) {
        val newsViewModel = hiltViewModel<NewsViewModel>()
        NewsScreen(
            newsViewModel = newsViewModel,
            onNewsCardClick = { articleUrl, _ -> navigateToDetails(articleUrl) }
        )
    }
}