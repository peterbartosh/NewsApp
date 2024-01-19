package com.example.newsapp.presentation.features.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.repository.LocalRepository
import com.example.newsapp.data.repository.NetworkRepository
import com.example.newsapp.data.utils.Constants.NEWS_ARTICLES_MAX_SIZE
import com.example.newsapp.data.utils.Constants.NEWS_ARTICLES_PER_PAGE
import com.example.newsapp.data.utils.QueryTopic
import com.example.newsapp.domain.Article
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
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
): ViewModel() {

    var dataFLow: Flow<PagingData<Article>> = emptyFlow()

    var lastQuery = QueryTopic.entries[0]

    init {
        fetchData()
    }

    fun fetchData(queryTopic: QueryTopic = lastQuery) = viewModelScope.launch {

        lastQuery = queryTopic

        dataFLow = Pager(
            config = PagingConfig(
                pageSize = NEWS_ARTICLES_PER_PAGE,
                maxSize = NEWS_ARTICLES_MAX_SIZE
            ),
            pagingSourceFactory = {
                NewsPagingSource(queryTopic, networkRepository, localRepository)
            }
        ).flow.distinctUntilChanged().flowOn(Dispatchers.IO).cachedIn(viewModelScope)
    }
}