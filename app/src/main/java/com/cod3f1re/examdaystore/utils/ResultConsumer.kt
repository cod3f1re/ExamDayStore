package com.cod3f1re.examdaystore.utils

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Utilidad para consumir el resultado de una petición, pasándolo a otras clases mediante su Callback.
 */
interface ResultConsumer<T> {

    /**
     * Callback para obtener la información del resultado de la petición.
     */
    fun consume(result: Result<T>, statusCode: NetworkCodes, error: Throwable? = null)

}
