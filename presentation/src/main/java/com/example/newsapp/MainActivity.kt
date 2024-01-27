package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapp.presentation.app.App
import com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl
import com.example.newsapp.presentation.features.details.DetailsViewModel
import com.example.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserverImpl

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun detailViewModelFactory(): DetailsViewModel.Factory
    }

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

