package com.cod3f1re.examdaystore.utils

import com.cod3f1re.examdaystore.R

/**
 * Códigos de respuesta posibles para peticiones a servicios web.
 * @param code Código de respuesta.
 * @param messageId Id en R referente al mensaje a mostrar para un código.
 */
enum class NetworkCodes constructor(val code: Int, val messageId: Int) {

    SUCCESS(201, R.string.code_200),
    NOT_FOUND(404, R.string.code_404),
    NO_CONNECTION(-2, R.string.not_conection);

    companion object {

        /**
         * Permite obtener un objeto de respuesta (código y id de mensaje) mediante un código.
         * @param code Código de respuesta numérico.
         * @return Objeto de respuesta que contiene el código numérico y el id del mensaje.
         */
        fun getStatusByCode(code: Int) = when (code) {
            201 -> SUCCESS
            404 -> NOT_FOUND
            else -> NO_CONNECTION
        }

    }

}
