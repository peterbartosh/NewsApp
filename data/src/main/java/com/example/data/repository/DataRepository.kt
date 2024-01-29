package com.example.data.repository

import com.example.common.QueryTopic
import com.example.data.model.common.DataArticle

interface DataRepository {
    suspend fun getArticles(
        page: Int,
        queryTopic: QueryTopic
    ): Result<List<DataArticle>>
    suspend fun getArticleByUrl(articleUrl: String): DataArticle?
}