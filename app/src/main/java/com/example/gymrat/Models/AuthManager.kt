package com.example.gymrat.Models

class AuthManager {
    var authToken: String? = null
        private set
    var userid: Int? = null
        private set

    companion object {
        val instance by lazy { AuthManager() }
    }

    fun setAuthToken(token: String) {
        authToken = token

    }
    fun setUserid(id: Int) {
        userid = id

    }

    fun clearAuthToken() {
        authToken = null
    }
}