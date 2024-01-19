package com.example.newsapp.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val url: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String,
    val queryTopicIndex: Int
)