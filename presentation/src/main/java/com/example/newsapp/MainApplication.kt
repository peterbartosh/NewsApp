package com.example.newsapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application(), ImageLoaderFactory {

    lateinit var connectivityObserver: ConnectivityObserverImpl

    override fun onCreate() {
        super.onCreate()
        connectivityObserver = ConnectivityObserverImpl(this.applicationContext)
    }

    override fun newImageLoader() =
        ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .respectCacheHeaders(false)
            .build()
}