package com.cod3f1re.examdaystore.model.repository.location

import com.cod3f1re.examdaystore.model.apientities.location.LocationRequest
import com.cod3f1re.examdaystore.model.apientities.location.LocationDataResponse
import com.cod3f1re.examdaystore.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositorio de datos para obtener información de la configuracion del usuario.
 * @param apiService Listado de servicios web consumibles.
 */
class LocationRepository(private val apiService: APIService) {
    /**
     * Realiza una petición al servicio web encargado de obtener el codigo para realizar el login.
     * @param token Contiene el token del usuario actual.
     */
    fun setLocationCode(data: LocationRequest, consumer: ResultConsumer<LocationDataResponse>) {
        apiService.locationGetCode(data).enqueue(object : Callback<LocationDataResponse> {
            override fun onResponse(
                call: Call<LocationDataResponse>,
                response: Response<LocationDataResponse>
            ) {
                val status = NetworkCodes.getStatusByCode(response.code())
                //Se espera el codigo exacto del endpoint y un cuerpo de respuesta valido
                if (status.code == 201 && response.body() != null) {
                    consumer.consume(Result.success(response.body()!!), status)
                } else {
                    consumer.consume(Result.error(data = null), status)
                }
            }

            override fun onFailure(call: Call<LocationDataResponse>, t: Throwable) {
                val error = if (!App.isNetworkAvailable) NetworkCodes.NO_CONNECTION
                else NetworkCodes.NOT_FOUND

                consumer.consume(Result.error(data = null), error)
            }

        })
    }
}