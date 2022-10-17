package com.cod3f1re.examdaystore.utils

import com.cod3f1re.examdaystore.model.apientities.location.LocationDataResponse
import com.cod3f1re.examdaystore.model.apientities.location.LocationRequest
import com.cod3f1re.examdaystore.model.apientities.login.LoginDataResponse
import com.cod3f1re.examdaystore.model.apientities.login.LoginRequest
import retrofit2.Call
import retrofit2.http.*


/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Listado de peticiones a los endpoints mediante Retrofit.
 */
interface APIService {
    /**
     * Petición para obtener el código de inicio de sesión.
     * @see LoginDataResponse
     */
    @POST("mobile/login/")
    fun loginGetCode(@Body stationsRequest: LoginRequest): Call<LoginDataResponse>

    /**
     * Petición para obtener el código al enviar ubicación.
     * @see LocationDataResponse
     */
    @POST("mobile/sendData/")
    fun locationGetCode(@Body stationsRequest: LocationRequest): Call<LocationDataResponse>
}