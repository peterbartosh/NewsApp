package com.example.data.repository

import com.example.common.QueryTopic
import com.example.data.model.dto.DataArticle
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.entities.ArticleEntity

interface DataRepository {
    suspend fun getArticles(
        page: Int,
        queryTopic: QueryTopic,
        transform: List<RemoteArticle>.() -> List<ArticleEntity>
    ): Result<List<DataArticle>>
    suspend fun getArticleByUrl(articleUrl: String): DataArticle?
}