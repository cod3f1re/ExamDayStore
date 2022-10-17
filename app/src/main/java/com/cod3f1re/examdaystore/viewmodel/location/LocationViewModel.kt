package com.cod3f1re.examdaystore.viewmodel.location

import com.cod3f1re.examdaystore.model.apientities.location.LocationRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cod3f1re.examdaystore.model.apientities.location.LocationDataResponse
import com.cod3f1re.examdaystore.model.repository.location.LocationRepository
import com.cod3f1re.examdaystore.utils.NetworkCodes
import com.cod3f1re.examdaystore.utils.Result
import com.cod3f1re.examdaystore.utils.ResultConsumer

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
class LocationViewModel(private val repository: LocationRepository) : ViewModel() {

    private val _locationLiveData: MutableLiveData<LocationDataResponse> = MutableLiveData()
    val locationLiveData: LiveData<LocationDataResponse> = _locationLiveData

    private val _errorLiveData: MutableLiveData<NetworkCodes?> = MutableLiveData()
    val errorLiveData: LiveData<NetworkCodes?> = _errorLiveData

    /**
     * Permite realizar una petición al servicio que proporciona el codigo, devolviendo una respuesta
     * satisfactoria o un error, segun sea el caso
     */
    fun getLocationCode(location : LocationRequest) {
        repository.setLocationCode(location,object : ResultConsumer<LocationDataResponse> {
            override fun consume(
                result: Result<LocationDataResponse>,
                statusCode: NetworkCodes,
                error: Throwable?
            ) {
                if (result.isSuccessful && statusCode == NetworkCodes.SUCCESS) {
                    _locationLiveData.postValue(result.data!!)
                } else{
                    _errorLiveData.postValue(statusCode)
                }
            }
        })
    }
}