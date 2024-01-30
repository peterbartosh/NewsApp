package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.entities.ArticleEntity

@Dao
interface LocalDao {

    @Query("Select * From cached_articles Where queryTopicIndex = :queryTopicIndex Order By id ASC Limit :limit Offset :offset")
    suspend fun getArticles(queryTopicIndex: Int, limit: Int = -1, offset: Int = 0): List<ArticleEntity>

    @Query("Select * From cached_articles Where url = :articleUrl")
    suspend fun getArticle(articleUrl: String): ArticleEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articleEntity: ArticleEntity)

    @Query("Delete From cached_articles Where queryTopicIndex = :queryTopicIndex")
    suspend fun clearAllByQuery(queryTopicIndex: Int)

}