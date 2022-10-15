package com.cod3f1re.examdaystore.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cod3f1re.examdaystore.model.apientities.login.LoginDataResponse
import com.cod3f1re.examdaystore.model.apientities.login.LoginRequest
import com.cod3f1re.examdaystore.model.repository.login.LoginRepository
import com.cod3f1re.examdaystore.utils.NetworkCodes
import com.cod3f1re.examdaystore.utils.Result
import com.cod3f1re.examdaystore.utils.ResultConsumer

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginLiveData: MutableLiveData<LoginDataResponse> = MutableLiveData()
    val loginLiveData: LiveData<LoginDataResponse> = _loginLiveData

    private val _errorLiveData: MutableLiveData<NetworkCodes?> = MutableLiveData()
    val errorLiveData: LiveData<NetworkCodes?> = _errorLiveData

    /**
     * Permite realizar una petición al servicio que proporciona el codigo, devolviendo una respuesta
     * satisfactoria o un error, segun sea el caso
     */
    fun getLoginGetCode(login : LoginRequest) {
        repository.getLoginGetCode(login,object : ResultConsumer<LoginDataResponse> {
                override fun consume(
                    result: Result<LoginDataResponse>,
                    statusCode: NetworkCodes,
                    error: Throwable?
                ) {
                    if (result.isSuccessful && statusCode == NetworkCodes.SUCCESS) {
                        _loginLiveData.postValue(result.data!!)
                    } else{
                        _errorLiveData.postValue(statusCode)
                    }

                }
            })
    }
}