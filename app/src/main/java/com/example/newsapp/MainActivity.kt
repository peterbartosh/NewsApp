package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapp.data.network.ConnectivityObserverImpl
import com.example.newsapp.presentation.app.App
import com.example.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserverImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = ConnectivityObserverImpl(this)
        setContent {
            NewsAppTheme {
                App(connectivityObserver)
            }
        }
    }
}

