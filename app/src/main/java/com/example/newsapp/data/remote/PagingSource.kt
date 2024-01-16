package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.repository.NetworkRepository
import com.example.newsapp.data.utils.QueryTopic
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.toNewsArticle
import okio.IOException
import retrofit2.HttpException

data class SearchKey(
    val page: Int,
    val query: QueryTopic
){
    fun next() = this.copy(page = this.page + 1)
    fun prev() = this.copy(page = this.page - 1)
}

class NewsPagingSource(
    private val initQueryTopic: QueryTopic,
    private val networkRepository: NetworkRepository
) : PagingSource<SearchKey, Article>() {

    override fun getRefreshKey(state: PagingState<SearchKey, Article>): SearchKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.next()
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.prev()
        }
    }

    override suspend fun load(params: LoadParams<SearchKey>): LoadResult<SearchKey, Article> {
        return try {

            val searchKey = params.key ?: SearchKey(1, initQueryTopic)

            val result = networkRepository.getLatestNews(page = searchKey.page, query = searchKey.query.name.lowercase())
            val temp = result.getOrNull()?.articles ?: emptyList()
            val articles = temp.mapNotNull { it.toNewsArticle() }

            LoadResult.Page(
                data = articles,
                prevKey = if (searchKey.page == 1) null else searchKey.prev(),
                nextKey = if (articles.isEmpty()) null else searchKey.next()
            )
        } catch (e: HttpException){
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}