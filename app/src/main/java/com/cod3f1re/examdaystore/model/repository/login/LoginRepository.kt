package com.cod3f1re.examdaystore.model.repository.login

import com.cod3f1re.examdaystore.model.apientities.login.LoginDataResponse
import com.cod3f1re.examdaystore.model.apientities.login.LoginRequest
import com.cod3f1re.examdaystore.utils.APIService
import com.cod3f1re.examdaystore.utils.App
import com.cod3f1re.examdaystore.utils.NetworkCodes
import com.cod3f1re.examdaystore.utils.ResultConsumer
import com.cod3f1re.examdaystore.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositorio de datos para obtener información de la configuracion del usuario.
 * @param apiService Listado de servicios web consumibles.
 */
class LoginRepository(private val apiService: APIService) {
    /**
     * Realiza una petición al servicio web encargado de obtener el codigo para realizar el login.
     * @param token Contiene el token del usuario actual.
     */
    fun getLoginGetCode(data: LoginRequest, consumer: ResultConsumer<LoginDataResponse>) {
        apiService.loginGetCode(data).enqueue(object : Callback<LoginDataResponse> {
            override fun onResponse(
                call: Call<LoginDataResponse>,
                response: Response<LoginDataResponse>
            ) {
                val status = NetworkCodes.getStatusByCode(response.code())
                if (status.code == 201 && response.body() != null) {
                    consumer.consume(Result.success(response.body()!!), status)
                } else {
                    consumer.consume(Result.error(data = null), status)
                }
            }

            override fun onFailure(call: Call<LoginDataResponse>, t: Throwable) {
                val error = if (!App.isNetworkAvailable) NetworkCodes.NO_CONNECTION
                else NetworkCodes.NOT_FOUND

                consumer.consume(Result.error(data = null), error)
            }

        })
    }
}