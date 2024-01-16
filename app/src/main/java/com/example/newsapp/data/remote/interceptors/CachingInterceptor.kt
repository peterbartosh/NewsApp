package com.example.newsapp.data.remote.interceptors

import android.content.Context
import com.example.newsapp.data.utils.Constants.CACHING_DURATION_IN_SECONDS
import com.example.newsapp.data.utils.isUserConnected
import com.example.newsapp.domain.ErrorCallbacks
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class CachingInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val errorCallbacks: ErrorCallbacks
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val cacheHeaderValue =
            if (context.isUserConnected())
                "public, max-age=$CACHING_DURATION_IN_SECONDS"
            else
                "public, only-if-cached, max-stale=$CACHING_DURATION_IN_SECONDS"
                    .also {
                        CoroutineScope(Dispatchers.Main).launch {
                            errorCallbacks.httpErrorCallback(403)
                        }
                    }

        val request = originalRequest.newBuilder().build()
        val response = chain.proceed(request)

        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheHeaderValue)
            .build()
    }
}
