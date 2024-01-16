package com.example.newsapp.presentation.features.details

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.newsapp.data.utils.Constants.DETAILS_SAVED_STATE_HANDLE_KEY
import com.example.newsapp.domain.Article

const val detailsRoute = "details"

fun NavController.navigateToDetails(article: Article, navOptions: NavOptions? = null){
    this.navigate(detailsRoute, navOptions)
    this.currentBackStackEntry?.savedStateHandle?.set(DETAILS_SAVED_STATE_HANDLE_KEY, article)
}

fun NavGraphBuilder.detailsScreen(){

    composable(route = detailsRoute){

        val detailsViewModel = hiltViewModel<DetailsViewModel>()

        val uiState = detailsViewModel.uiState.collectAsState()

        val article = it.savedStateHandle.get<Article>(DETAILS_SAVED_STATE_HANDLE_KEY)

        LaunchedEffect(key1 = true){
            detailsViewModel.loadImage(article)
        }

        DetailsScreen(uiState)
    }
}