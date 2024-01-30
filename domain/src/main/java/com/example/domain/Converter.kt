package com.example.domain

import com.example.data.model.common.DataArticle

fun DataArticle.toArticle(): Article? {
    return Article(
        author = this.author?.ifBlank { return null } ?: return null,
        description = this.description?.ifBlank { return null } ?: return null,
        publishedAt = this.publishedAt?.ifBlank { return null } ?: return null,
        title = this.title?.ifBlank { return null } ?: return null,
        url = this.url?.ifBlank { return null } ?: return null,
        urlToImage = this.urlToImage?.ifBlank { return null } ?: return null,
    )
}
