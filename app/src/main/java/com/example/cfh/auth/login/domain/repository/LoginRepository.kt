package com.example.cfh.auth.login.domain.repository

interface LoginRepository {

    suspend fun login(email: String, password: String): Boolean

}