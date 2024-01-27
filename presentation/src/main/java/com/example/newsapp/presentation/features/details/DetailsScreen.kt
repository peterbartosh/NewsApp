package com.example.newsapp.presentation.features.details

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.domain.Article
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.components.Loading
import com.example.newsapp.presentation.components.UiState
import com.example.newsapp.presentation.features.details.components.SucceedScreenData


@Composable
fun DetailsScreen(
    fraction: Float = 1.0f,
    uiState: UiState<Article>
) {

    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            when (uiState) {
                is UiState.Success -> {
                    SucceedScreenData(uiState.data!!)
                }

                is UiState.Failure -> {
                    ErrorOccurred(errorType = uiState.errorType!!)
                }

                is UiState.Loading -> {
                    Loading()
                }
            }
        }
    }
}