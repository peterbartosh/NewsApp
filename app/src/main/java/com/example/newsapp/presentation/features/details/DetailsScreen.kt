package com.example.newsapp.presentation.features.details

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.data.utils.UiState
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.components.Loading
import com.example.newsapp.presentation.features.details.components.SucceedScreenData
import com.example.newsapp.presentation.features.details.DetailsViewModel


@Composable
fun DetailsScreen(
    fraction: Float = 1.0f,
    article: Article?,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
) {

    val uiState = detailsViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = article){
        detailsViewModel.loadImage(article)
    }

    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            when (uiState.value) {
                is UiState.Success -> {
                    SucceedScreenData(uiState.value.data!!)
                }

                is UiState.Failure -> {
                    ErrorOccurred(errorType = uiState.value.errorType!!)
                }

                is UiState.Loading -> {
                    Loading()
                }
            }
        }
    }
}