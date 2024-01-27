package com.example.newsapp.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.content.ContextCompat.startActivity
import com.example.common.ErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.newsapp.R as Res

sealed class UiState<T>(open val data: T? = null, open val errorType: ErrorType? = null){
    class Success<T>(override val data: T? = null): UiState<T>(data = data)
    class Failure<T>(override val errorType: ErrorType): UiState<T>(errorType = errorType)
    class Loading<T> : UiState<T>()
}

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

// Browse article
fun Context.browse(link: String){
    val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(this, browseIntent, null)
}

// Formats timestamp string representation
fun formatDateString(timestampString: String) = try {
    timestampString.split("T").first()
} catch (nfe: NumberFormatException){
    ""
}

fun Context.explainErrorResponse(code: Int) =
    when (code){
        400 -> this.getString(Res.string.bad_req_error)
        401 -> this.getString(Res.string.auth_error)
        403 -> this.getString(Res.string.connection_lost_error)
        426 -> this.getString(Res.string.too_many_results_error)
        429 -> this.getString(Res.string.too_many_req_error)
        500 -> this.getString(Res.string.server_error)
        else -> this.getString(Res.string.unknown_error)
    }

@Composable
fun isTablet(): Boolean {
    return LocalConfiguration.current.screenWidthDp >= 700
}

suspend fun Context.errorCallback(code: Int) {
    withContext(Dispatchers.Main) {
        val message = this@errorCallback.explainErrorResponse(code)
        this@errorCallback.showToast(this@errorCallback.getString(Res.string.pretty_guy) + " $message")
    }
}