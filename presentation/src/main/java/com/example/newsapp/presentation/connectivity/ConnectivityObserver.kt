package com.example.newsapp.presentation.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    fun isUserConnected() : Boolean

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}