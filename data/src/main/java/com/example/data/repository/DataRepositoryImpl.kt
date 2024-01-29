package com.example.data.repository

import com.example.common.CustomException
import com.example.common.ErrorType
import com.example.common.QueryTopic
import com.example.data.components.toArticleEntity
import com.example.data.model.common.DataArticle
import com.example.data.source.LocalSource
import com.example.data.source.RemoteSource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
): DataRepository {

    override suspend fun getArticles(
        page: Int,
        queryTopic: QueryTopic
    ): Result<List<DataArticle>> {

        val apiResult = remoteSource.getLatestNews(
            page = page,
            queryTopic = queryTopic
        )

        val articles = if (apiResult.isSuccess) {
            val remoteArticles = apiResult.getOrNull()?.articles ?: emptyList()
            if (page == 1 && remoteArticles.isNotEmpty())
                localSource.clearAllByQuery(queryTopic)
            localSource.insertAll(remoteArticles.mapNotNull { it.toArticleEntity(queryTopic) })
            remoteArticles
        } else {
            localSource.getArticles(queryTopic = queryTopic, page = page)
        }

        return if (articles.isNotEmpty())
            Result.success(articles)
        else
            Result.failure(apiResult.exceptionOrNull() ?: CustomException(ErrorType.EmptyResult, -1))
    }

    override suspend fun getArticleByUrl(articleUrl: String): DataArticle? {
        return localSource.getArticle(articleUrl)
    }

}