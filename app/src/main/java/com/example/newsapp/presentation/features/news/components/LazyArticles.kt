package com.example.newsapp.presentation.features.news.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.components.Loading


@Composable
fun LazyNews(articles: LazyPagingItems<Article>, onNewsCardClick: (Article) -> Unit) {

    val scrollState = rememberLazyListState()

    if (articles.loadState.refresh is LoadState.Loading)
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Loading()
        }

    LazyColumn(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            ),
        state = scrollState
    ) {

        itemsIndexed(
            items = articles,
            key = { ind, item -> item.url + ind.toString() }
        ) { index, article ->
            if (article != null) {
                ArticleCard(
                    article = article,
                    isFirst = index == 0,
                    isLast = articles.itemCount == index + 1,
                    isLoadingData = articles.loadState.refresh is LoadState.Loading,
                    onClick = {
                        onNewsCardClick(article)
                    }
                )
            }
        }

        if (articles.loadState.append is LoadState.Loading)
            item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)) {
                        Loading()
                    }
            }
    }
}