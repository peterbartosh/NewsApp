package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.remote.interceptors.AuthorizationInterceptor
import com.example.newsapp.domain.ErrorCallbacks
import com.example.newsapp.data.remote.interceptors.CachingInterceptor
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.utils.Constants.CACHE_MAX_SIZE
import com.example.newsapp.data.utils.Constants.NEWS_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // remote
    @Provides @Singleton
    fun provideCallbacks(@ApplicationContext context: Context) = ErrorCallbacks(context)

    @Provides @Singleton
    fun provideAuthorizationInterceptor() = AuthorizationInterceptor()

    @Provides @Singleton
    fun provideForceCachingInterceptor(
        @ApplicationContext context: Context,
        errorCallbacks: ErrorCallbacks
    ) = CachingInterceptor(context, errorCallbacks)

    @Provides @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        authorizationInterceptor: AuthorizationInterceptor,
        cachingInterceptor: CachingInterceptor
    ) = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_MAX_SIZE))
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(cachingInterceptor)
            .addNetworkInterceptor(cachingInterceptor)
            .build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)

}