package com.cod3f1re.examdaystore.utils

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Clase que representa la aplicación Android.
 */
class App : Application() {

    companion object {

        /**
         * Instancia de la aplicación
         */
        lateinit var instance: App
            private set

        /**
         * Estado de la conectividad de red.
         */
        var isNetworkAvailable = false
            private set


    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

        instance = this

        //Callback para detectar el estado de la red
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isNetworkAvailable = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isNetworkAvailable = false
            }

            override fun onUnavailable() {
                super.onUnavailable()
                isNetworkAvailable = false
            }
        })

    }

}
