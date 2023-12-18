package com.example.newsapp.domain

import com.example.newsapp.data.model.dto.RemoteArticle
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

fun RemoteArticle.filterNullableProps(): RemoteArticle? {
    val notValid = this::class.memberProperties.any{ prop ->
        prop.getter.call(this) == null
    }
    return if (notValid) null else this
}