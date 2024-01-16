package com.example.newsapp.presentation.features.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.data.utils.UiState
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.ErrorOccurred
import com.example.newsapp.presentation.components.Loading
import com.example.newsapp.presentation.features.details.components.SucceedScreenData

@Composable
fun DetailsScreen(
    uiState: State<UiState<Article>>,
) {


    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
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

//@Preview
//@Composable
//fun DetailsScreenPreview() {
//    val uiState = remember {
//        mutableStateOf(UiState.Success(
//            data = NewsArticle(
//                author = "AUTHOR",
//                title = "title title  title  title  title  title  title  title ",
//                content = "",
//                description = "description description description description description description description description",
//                publishedAt = "22.05.2023",
//                url = "https://news.ru/culture/nikogda-ne-zhalovalsya-kak-bronevoj-perezhil-arest-otca-i-smert-zheny/",
//                urlToImage = "https://static.news.ru/photo/401d8a16-9a95-11ee-ba51-02420a0000c9_1024.jpg"
//            )
//        ))
//    }
//    DetailsScreen(uiState = uiState)
//}