package com.example.discover_destination.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

fun Any.logi(message: Any? = "no message!") {
    Log.i(this.javaClass.simpleName, message.toString())
}

fun Any.logd(message: Any? = "no message!", cause: Throwable? = null) {
    Log.d(this.javaClass.simpleName, message.toString(), cause)
}

fun Any.logw(message: Any? = "no message!", cause: Throwable? = null) {
    Log.w(this.javaClass.simpleName, message.toString(), cause)
}

fun Any.loge(message: Any? = "no message!", cause: Throwable? = null) {
    Log.e(this.javaClass.simpleName, message.toString(), cause)
}

fun networkConnectivity(context: Context, syncProducts: () -> Unit? = {}): Boolean {
    val cm = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    cm?.let{
        it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                //take action when network connection is gained
                syncProducts()
            }
        })
    }
    if (cm != null) {
        val capabilities =
            cm.getNetworkCapabilities(cm.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}