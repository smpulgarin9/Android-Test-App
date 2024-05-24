package com.example.androidtestapp.util
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Utility object to check network connectivity.
 */
object NetworkUtils {

    /**
     * Checks if the device is connected to the internet.
     *
     * @param context The application context.
     * @return True if the device is online, false otherwise.
     */
    fun isOnline(context: Context): Boolean {
        // Get the ConnectivityManager from the system service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Get the current active network
        val network = connectivityManager.activeNetwork ?: return false
        // Get the capabilities of the active network
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        // Check if the network has transport capabilities for WiFi or Cellular
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}

