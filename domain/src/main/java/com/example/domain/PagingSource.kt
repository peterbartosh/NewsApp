package com.example.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.common.ErrorType
import com.example.common.LightWeightException
import com.example.common.QueryTopic
import com.example.data.repository.LocalRepository
import com.example.data.repository.NetworkRepository


class NewsPagingSource(
    private val initQueryTopic: QueryTopic,
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
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

        val result = networkRepository.getLatestNews(
            page = searchKey.page,
            query = searchKey.query.name.lowercase()
        )

        return if (result.isSuccess) {

            val articles =
                result.getOrNull()?.articles?.mapNotNull { it.toNewsArticle() } ?: emptyList()

            if (searchKey.page == 1) localRepository.clearAllByQuery(searchKey.query)
            localRepository.insertAll(articles.map { it.toArticleEntity(searchKey.query) })

            LoadResult.Page(
                data = articles,
                prevKey = if (searchKey.page == 1) null else searchKey.prev(),
                nextKey = if (articles.isEmpty()) null else searchKey.next()
            )
        } else {
            val articles = localRepository
                .getArticles(queryTopic = searchKey.query, page = searchKey.page)
                .map { it.toNewsArticle() }

            if (articles.isNotEmpty())
                LoadResult.Page(
                    data = articles,
                    prevKey = if (searchKey.page == 1) null else searchKey.prev(),
                    nextKey = if (articles.isEmpty()) null else searchKey.next()
                )
            else
                LoadResult.Error(
                    result.exceptionOrNull() ?: LightWeightException(ErrorType.EmptyResult, -1)
                )
        }
    }
}
