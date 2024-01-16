package com.example.newsapp.data.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.content.ContextCompat.startActivity
import com.example.newsapp.R as Res


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

// Supporting low minSdk for future issues
fun Context.isUserConnected() : Boolean {

    val hasInternet: Boolean

    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        hasInternet = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        hasInternet = try {
            if (connectivityManager.activeNetworkInfo == null) {
                false
            } else {
                connectivityManager.activeNetworkInfo?.isConnected!!
            }
        } catch (e: Exception) {
            false
        }
    }
    return hasInternet
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
    return LocalConfiguration.current.screenWidthDp >= 600
}