package com.example.newsapp.data.model.dto

data class ErrorResponse(
    val code: String,
    val message: String,
    override val status: String
): ApiResponse