package com.example.cfh.auth.register.domain.repository

interface RegisterRepository {
    suspend fun register(email: String, password: String): Boolean

}