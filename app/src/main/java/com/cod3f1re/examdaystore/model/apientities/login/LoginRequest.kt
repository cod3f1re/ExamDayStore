package com.cod3f1re.examdaystore.model.apientities.login

data class LoginRequest (
    var email: String,
    var pass: String,
    var name: String
)