package com.example.gymrat.Models

data class LoginResponse(
    val error : Boolean,
    val message: String,
    val token : String,
    val data : User
)
