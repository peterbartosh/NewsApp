package com.example.newsapp.domain

import android.content.Context
import android.util.Log
import com.example.newsapp.data.utils.explainErrorResponse
import com.example.newsapp.R as Res
import com.example.newsapp.data.utils.showToast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ErrorCallbacks @Inject constructor(
    private val context: Context
) {

    suspend fun httpErrorCallback(code: Int){
        withContext(Dispatchers.Main) {
            Log.d("ERROR_CALL", "httpErrorCallback: ")
            val message = context.explainErrorResponse(code)
            context.showToast(context.getString(Res.string.pretty_guy) + " $message")
        }
    }

    suspend fun connectionLostErrorCallback() {
        withContext(Dispatchers.Main) {
            Log.d("ERROR_CALL", "connectionLostErrorCallback: ")
            context.showToast(context.getString(Res.string.pretty_guy) + " " + context.getString(Res.string.connection_lost_error))
        }
    }
}