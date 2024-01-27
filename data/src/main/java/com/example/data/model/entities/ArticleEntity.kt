package com.example.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.dto.DataArticle

@Entity(tableName = "cached_articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    override val url: String,
    override val author: String,
    override val description: String,
    override val publishedAt: String,
    override val title: String,
    override val urlToImage: String,
    val queryTopicIndex: Int
): DataArticle