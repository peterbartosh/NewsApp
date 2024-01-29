package com.example.domain

import com.example.data.components.filterNullableProps
import com.example.data.model.common.DataArticle

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