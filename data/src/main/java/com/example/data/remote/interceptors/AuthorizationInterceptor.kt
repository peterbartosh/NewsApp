package com.example.data.remote.interceptors

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", BuildConfig.NEWS_API_KEY)
            .build()

        return chain.proceed(request)
    }
}