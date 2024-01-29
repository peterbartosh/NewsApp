package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapp.presentation.app.App
import com.example.newsapp.presentation.features.details.DetailsViewModel
import com.example.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun detailViewModelFactory(): DetailsViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val connectivityObserver = (this.applicationContext as MainApplication).connectivityObserver
            NewsAppTheme {
                App(connectivityObserver)
            }
        }
    }
}

