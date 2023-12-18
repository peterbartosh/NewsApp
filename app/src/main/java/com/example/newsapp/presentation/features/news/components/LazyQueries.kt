package com.example.newsapp.presentation.features.news.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.utils.QueryTopic

@Composable
fun LazyQueries(onQueryButtonClick: (QueryTopic) -> Unit) {

    val scrollState = rememberLazyListState()

    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.padding(horizontal = 5.dp), state = scrollState) {
         itemsIndexed(QueryTopic.entries, key = { _, it -> it.name }){ index, queryTopic ->
             QueryButton(
                 isSelected = index == selectedIndex,
                 query = queryTopic.name,
                 onClick = {
                     selectedIndex = index
                     onQueryButtonClick(queryTopic)
                }
             )
         }
    }

}