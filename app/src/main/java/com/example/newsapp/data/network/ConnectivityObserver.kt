package com.example.newsapp.data.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    fun isUserConnected() : Boolean

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}