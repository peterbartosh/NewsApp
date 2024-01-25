package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.LocalDao
import com.example.newsapp.data.local.LocalDatabase
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.interceptors.AuthorizationInterceptor
import com.example.newsapp.data.repository.LocalRepository
import com.example.newsapp.data.utils.Constants.CACHE_MAX_SIZE
import com.example.newsapp.data.utils.Constants.NEWS_API_BASE_URL
import com.google.gson.Gson
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
    fun provideAuthorizationInterceptor() = AuthorizationInterceptor()

    @Provides @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        authorizationInterceptor: AuthorizationInterceptor,
    ) = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_MAX_SIZE))
            .addInterceptor(authorizationInterceptor)
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


    // local
    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room
            .databaseBuilder(context, LocalDatabase::class.java, "caching_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides @Singleton
    fun provideLocalDao(localDatabase: LocalDatabase) = localDatabase.provideDao()

    @Provides @Singleton
    fun provideLocalRepository(localDao: LocalDao) = LocalRepository(localDao)

    // common
    @Provides @Singleton
    fun provideGson() = Gson()

}