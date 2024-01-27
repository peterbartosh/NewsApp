package com.example.data.remote


import com.example.data.components.Constants.CACHING_DURATION_IN_SECONDS
import com.example.data.components.Constants.NEWS_ARTICLES_PER_PAGE
import com.example.data.model.dto.SuccessfulResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
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