package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.entities.ArticleEntity

@Dao
interface LocalDao {

    @Query("Select * From cached_articles Where queryTopicIndex = :queryTopicIndex Order By id ASC Limit :limit Offset :offset")
    suspend fun getArticles(queryTopicIndex: Int, limit: Int, offset: Int): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articleEntity: ArticleEntity)

    @Query("Delete From cached_articles")
    suspend fun clearAll()

    @Query("Delete From cached_articles Where queryTopicIndex = :queryTopicIndex")
    suspend fun clearAllByQuery(queryTopicIndex: Int)

}