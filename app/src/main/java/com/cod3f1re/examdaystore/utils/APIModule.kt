package com.cod3f1re.examdaystore.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Módulo que permite inicializar una instancia de Retrofit para realizar peticiones.
 */
class APIModule {

    companion object {

        /**
         * Genera y devuelve una instancia de Retrofit como Singleton.
         */
        fun getAPIService(): APIService =
            Retrofit.Builder()
                .baseUrl(APIConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

    }

}