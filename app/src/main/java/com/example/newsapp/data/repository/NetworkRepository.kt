package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.model.dto.ErrorResponse
import com.example.newsapp.domain.ErrorCallbacks
import com.example.newsapp.data.remote.NewsApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class NetworkRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val errorCallbacks: ErrorCallbacks
) {

    suspend fun getLatestNews(query: String, page: Int) = withContext(Dispatchers.IO){
        var errorCode = -1
        var errorMessage = ""
        try {
            val response = newsApi.getLatestNews(query = query, page = page)

            if (response.isSuccessful){
                Result.success(response.body())
            } else {
                errorCode = response.code()
                errorMessage = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java).message
                Result.failure(HttpException(response))
            }
        } catch (httpE: HttpException){
            errorCode = httpE.code()
            errorMessage = httpE.message()
            Result.failure(httpE)
        } finally {
            if (errorCode != -1){
                errorCallbacks.httpErrorCallback(errorCode)
                Log.d("ERROR_ERROR", "getLatestNews: $errorCode: $errorMessage")
            }
        }
    }

}