package com.example.common

enum class ErrorType {
    Http, Connection, EmptyResult;
}

enum class QueryTopic {
    Politics, Business, Technology, Science, IT, Health, Fashion, Art;
}

class LightWeightException(val errorType: ErrorType, val code: Int): Throwable()
