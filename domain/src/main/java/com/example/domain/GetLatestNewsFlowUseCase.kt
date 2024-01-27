package com.example.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.common.QueryTopic
import com.example.data.components.Constants.NEWS_ARTICLES_MAX_SIZE
import com.example.data.components.Constants.NEWS_ARTICLES_PER_PAGE
import com.example.data.repository.DataRepository
import javax.inject.Inject

class GetLatestNewsFlowUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(queryTopic: QueryTopic) = Pager(
        config = PagingConfig(
            pageSize = NEWS_ARTICLES_PER_PAGE,
            maxSize = NEWS_ARTICLES_MAX_SIZE
        ),
        pagingSourceFactory = {
            NewsPagingSource(
                queryTopic,
                dataRepository
            )
        }
    ).flow
}