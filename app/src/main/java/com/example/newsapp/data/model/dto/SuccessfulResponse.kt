package com.example.newsapp.data.model.dto

data class SuccessfulResponse(
    val articles: List<RemoteArticle>,
    override val status: String,
    val totalResults: Int
): ApiResponse