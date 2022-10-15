package com.cod3f1re.examdaystore.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cod3f1re.examdaystore.model.repository.login.LoginRepository

/**
 * Permite crear un ConfigurationViewModel pasándole un [repository] como parámetro.
 */
@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: LoginRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(repository) as T
        } else throw IllegalArgumentException("ViewModel no encontrado")
    }

}
