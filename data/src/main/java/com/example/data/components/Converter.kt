package com.example.data.components

import com.example.common.QueryTopic
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.entities.ArticleEntity

fun RemoteArticle.toArticleEntity(queryTopic: QueryTopic): ArticleEntity? {
    return ArticleEntity(
        author = this.author?.ifBlank { return null } ?: return null,
        description = this.description?.ifBlank { return null } ?: return null,
        publishedAt = this.publishedAt?.ifBlank { return null } ?: return null,
        title = this.title?.ifBlank { return null } ?: return null,
        url = this.url?.ifBlank { return null } ?: return null,
        urlToImage = this.urlToImage?.ifBlank { return null } ?: return null,
        queryTopicIndex = queryTopic.ordinal
    )
}