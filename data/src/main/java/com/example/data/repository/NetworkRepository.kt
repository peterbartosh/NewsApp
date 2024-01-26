package com.example.data.repository

import android.util.Log
import com.example.common.ErrorType
import com.example.common.LightWeightException
import com.example.data.model.dto.ErrorResponse
import com.example.data.remote.NewsApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class NetworkRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val gson: Gson
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
                errorMessage = gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java).message
                Result.failure(HttpException(response))
            }
        } catch (httpE: HttpException){
            errorCode = httpE.code()
            errorMessage = httpE.message()
            Result.failure(LightWeightException(ErrorType.Http, httpE.code()))
        } catch (ioE: IOException) {
            errorCode = 403
            errorMessage = "No internet connection"
            Result.failure(LightWeightException(ErrorType.Connection, 403))
        } finally {
            if (errorCode != -1){
                Log.d("ERROR_ERROR", "getLatestNews: $errorCode: $errorMessage")
            }
        }
    }
}

