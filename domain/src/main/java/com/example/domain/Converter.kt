package com.example.domain

import com.example.common.QueryTopic
import com.example.data.model.dto.DataArticle
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.entities.ArticleEntity
import kotlin.reflect.full.memberProperties

fun DataArticle.toArticle() = this.filterNullableProps()?.let { dataArticle ->
    Article(
        author = dataArticle.author!!,
        description = dataArticle.description!!,
        publishedAt = dataArticle.publishedAt!!,
        title = dataArticle.title!!,
        url = dataArticle.url!!,
        urlToImage = dataArticle.urlToImage!!
    )
}

fun Article.toArticleEntity(queryTopic: QueryTopic) =
    ArticleEntity(
        author = author,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage,
        queryTopicIndex = queryTopic.ordinal
    )

fun RemoteArticle.toArticleEntity(queryTopic: QueryTopic) =
    this.filterNullableProps()?.let { dataArticle ->
        ArticleEntity(
            author = dataArticle.author!!,
            description = dataArticle.description!!,
            publishedAt = dataArticle.publishedAt!!,
            title = dataArticle.title!!,
            url = dataArticle.url!!,
            urlToImage = dataArticle.urlToImage!!,
            queryTopicIndex = queryTopic.ordinal
        )
}


fun DataArticle.filterNullableProps(): DataArticle? {
    val notValid = this::class.memberProperties.any{ prop ->
        prop.getter.call(this) == null
    }
    return if (notValid) null else this
}