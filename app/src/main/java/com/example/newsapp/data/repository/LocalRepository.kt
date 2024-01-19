package com.example.newsapp.data.repository

import com.example.newsapp.data.local.LocalDao
import com.example.newsapp.data.model.entities.ArticleEntity
import com.example.newsapp.data.utils.Constants.NEWS_ARTICLES_PER_PAGE
import com.example.newsapp.data.utils.QueryTopic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(private val localDao: LocalDao) {

    suspend fun getArticles(queryTopic: QueryTopic, page: Int) = withContext(Dispatchers.IO){
        localDao.getArticles(queryTopic.ordinal, limit = page * NEWS_ARTICLES_PER_PAGE, offset = (page - 1) * NEWS_ARTICLES_PER_PAGE)
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