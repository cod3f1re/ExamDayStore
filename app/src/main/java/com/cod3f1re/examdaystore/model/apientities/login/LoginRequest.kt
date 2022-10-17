package com.cod3f1re.examdaystore.model.apientities.login

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
data class LoginRequest (
    var email: String,
    var pass: String,
    var name: String
)