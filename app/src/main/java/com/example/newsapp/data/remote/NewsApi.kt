package com.example.newsapp.data.remote


import com.example.newsapp.data.model.dto.SuccessfulResponse
import com.example.newsapp.data.utils.Constants
import com.example.newsapp.data.utils.Constants.CACHING_DURATION_IN_SECONDS
import com.example.newsapp.data.utils.Constants.NEWS_ARTICLES_PER_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getLatestNews(
        @Header("Cache-Control") cachedControl: String = "max-age=$CACHING_DURATION_IN_SECONDS",
        @Query("q") query: String,

        @Query("language") country: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = NEWS_ARTICLES_PER_PAGE,
        @Query("page") page: Int,
    ): Response<SuccessfulResponse>
}