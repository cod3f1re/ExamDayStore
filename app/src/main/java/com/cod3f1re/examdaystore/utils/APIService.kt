package com.cod3f1re.examdaystore.utils

import com.cod3f1re.examdaystore.model.apientities.login.LoginDataResponse
import com.cod3f1re.examdaystore.model.apientities.login.LoginRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


/**
 * Listado de peticiones a los endpoints mediante Retrofit.
 */
interface APIService {
    /**
     * Petición para obtener los banners publicitarios.
     * @see LoginDataResponse
     */
    @POST("mobile/login/")
    fun loginGetCode(@Body stationsRequest: LoginRequest): Call<LoginDataResponse>
}