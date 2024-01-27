package com.example.data.model.dto

data class RemoteArticle(
    override val author: String?,
    val content: String?,
    override val description: String?,
    override val publishedAt: String?,
    val source: Source?,
    override val title: String?,
    override val url: String?,
    override val urlToImage: String?
): DataArticle