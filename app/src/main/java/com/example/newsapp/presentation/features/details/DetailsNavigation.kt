package com.example.newsapp.presentation.features.details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.newsapp.data.utils.Constants.DETAILS_SAVED_STATE_HANDLE_KEY

const val detailsRoute = "details"

fun NavController.navigateToDetails(articleUrl: String, navOptions: NavOptions? = null){
    this.navigate(detailsRoute, navOptions)
    this.currentBackStackEntry?.savedStateHandle?.set(DETAILS_SAVED_STATE_HANDLE_KEY, articleUrl)
}

fun NavGraphBuilder.detailsScreen(){

    composable(route = detailsRoute){

        val detailsViewModel = hiltViewModel<DetailsViewModel>()

        val articleUrl = it.savedStateHandle.get<String>(DETAILS_SAVED_STATE_HANDLE_KEY)

        DetailsScreen(detailsViewModel = detailsViewModel, articleUrl = articleUrl)
    }
}