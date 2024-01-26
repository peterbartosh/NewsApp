package com.example.domain

import com.example.common.QueryTopic
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.entities.ArticleEntity
import kotlin.reflect.full.memberProperties

fun RemoteArticle.toNewsArticle() = this.filterNullableProps()?.let { article ->
    Article(
        author = article.author!!,
        description = article.description!!,
        publishedAt = article.publishedAt!!,
        title = article.title!!,
        url = article.url!!,
        urlToImage = article.urlToImage!!
    )
}

fun ArticleEntity.toNewsArticle() =
    Article(
        author = author,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )

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


fun RemoteArticle.filterNullableProps(): RemoteArticle? {
    val notValid = this::class.memberProperties.any{ prop ->
        prop.getter.call(this) == null
    }
    return if (notValid) null else this
}