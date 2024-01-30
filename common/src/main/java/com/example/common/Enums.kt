package com.example.common

enum class ErrorType {
    Http, Connection, EmptyResult;
}

enum class QueryTopic {
    Politics, Business, Technology, Science, IT, Health, Fashion, Art;
}

data class CustomException(val errorType: ErrorType, val code: Int): Throwable()
