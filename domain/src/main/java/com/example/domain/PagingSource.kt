package com.example.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.common.CustomException
import com.example.common.ErrorType
import com.example.common.QueryTopic
import com.example.data.repository.DataRepository


class NewsPagingSource(
    private val initQueryTopic: QueryTopic,
    private val dataRepository: DataRepository
) : PagingSource<NewsPagingSource.SearchKey, Article>() {

    data class SearchKey(
        val page: Int,
        val query: QueryTopic
    ){
        fun next() = this.copy(page = this.page + 1)
        fun prev() = this.copy(page = this.page - 1)
    }

    override fun getRefreshKey(state: PagingState<SearchKey, Article>): SearchKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.next()
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.prev()
        }
    }

    override suspend fun load(params: LoadParams<SearchKey>): LoadResult<SearchKey, Article> {
        val searchKey = params.key ?: SearchKey(1, initQueryTopic)

        val result = dataRepository.getArticles(
            page = searchKey.page,
            queryTopic = searchKey.query
        )

        val articles = result.getOrNull()?.mapNotNull { it.toArticle() }

        return if (result.isSuccess && !articles.isNullOrEmpty()) {
            LoadResult.Page(
                data = articles,
                prevKey = if (searchKey.page == 1) null else searchKey.prev(),
                nextKey = if (articles.isEmpty()) null else searchKey.next()
            )
        }
        else
            LoadResult.Error(
                result.exceptionOrNull() ?: CustomException(ErrorType.EmptyResult, -1)
            )
    }
}
