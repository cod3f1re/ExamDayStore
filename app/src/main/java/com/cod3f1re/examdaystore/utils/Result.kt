package com.cod3f1re.examdaystore.utils

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
/**
 * Clase que permite consumir la información de respuesta de una petición o los errores
 * asociados a esta.
 * @param data Información de la petición.
 * @param error Error asociado a la petición.
 */
class Result<T>(val data: T? = null, val error: Throwable? = null) {

    val isSuccessful = data != null && error == null

    companion object {

        /**
         * Genera un resultado exitoso.
         * @param data Información del resultado.
         * @return Un resultado con estado exitoso y que contiene la información de este.
         */
        fun <T> success(data: T) = Result(data)

        /**
         * Genera un resultado con error.
         * @param t El error asociado al resultado.
         * @return Un resultado fallido con información del error.
         */
        fun error(t: Throwable?) = Result(t)

        /**
         * Genera un resultado con error e información adicional.
         * @param data La información adicional sobre el resultado fallido.
         * @return Un resultado fallido con información adicional.
         */
        fun <T> error(data: T?) = Result(data)

    }

}
