package com.example.newsapp.presentation.features.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.common.QueryTopic
import com.example.domain.Article
import com.example.domain.GetLatestNewsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getLatestNewsFlowUseCase: GetLatestNewsFlowUseCase
): ViewModel() {

    var dataFLow: Flow<PagingData<Article>> = emptyFlow()

    var lastQuery = QueryTopic.entries[0]

    init {
        fetchData()
    }

    fun fetchData(queryTopic: QueryTopic = lastQuery) = viewModelScope.launch {

        lastQuery = queryTopic

        dataFLow = getLatestNewsFlowUseCase.invoke(queryTopic)
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
    }
}