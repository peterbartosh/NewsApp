package com.example.newsapp.presentation.features.news.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.QueryTopic

@Composable
fun LazyQueries(selectedIndex: Int, modifier: Modifier = Modifier, onQueryButtonClick: (Int, QueryTopic) -> Unit) {

    val scrollState = rememberLazyListState()

    LazyRow(modifier = modifier.padding(horizontal = 5.dp), state = scrollState) {
         itemsIndexed(QueryTopic.entries, key = { _, it -> it.name }){ index, queryTopic ->
             QueryButton(
                 isSelected = index == selectedIndex,
                 query = queryTopic.name,
                 onClick = { onQueryButtonClick(index, queryTopic) }
             )
         }
    }

}