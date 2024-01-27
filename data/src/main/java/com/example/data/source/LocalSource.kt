package com.example.data.source

import com.example.common.QueryTopic
import com.example.data.components.Constants.NEWS_ARTICLES_PER_PAGE
import com.example.data.local.LocalDao
import com.example.data.model.entities.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalSource @Inject constructor(private val localDao: LocalDao) {

    suspend fun getArticles(queryTopic: QueryTopic, page: Int) = withContext(Dispatchers.IO){
        localDao.getArticles(queryTopic.ordinal, limit = page * NEWS_ARTICLES_PER_PAGE, offset = (page - 1) * NEWS_ARTICLES_PER_PAGE)
    }

    suspend fun getArticle(articleUrl: String) = withContext(Dispatchers.IO){
        localDao.getArticle(articleUrl)
    }

    suspend fun insertAll(articleEntities: List<ArticleEntity>) = withContext(Dispatchers.IO){
        articleEntities.forEach { articleEntity ->
            localDao.insertArticle(articleEntity)
        }
    }

    suspend fun clearAll() = withContext(Dispatchers.IO){
        localDao.clearAll()
    }

    suspend fun clearAllByQuery(queryTopic: QueryTopic) = withContext(Dispatchers.IO){
        localDao.clearAllByQuery(queryTopic.ordinal)
    }

}