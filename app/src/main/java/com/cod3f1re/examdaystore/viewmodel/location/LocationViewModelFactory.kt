package com.cod3f1re.examdaystore.viewmodel.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cod3f1re.examdaystore.model.repository.location.LocationRepository

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Permite crear un LoginViewModel pasándole un [repository] como parámetro.
 */
@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory(private val repository: LocationRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            LocationViewModel(repository) as T
        } else throw IllegalArgumentException("ViewModel no encontrado")
    }
}
