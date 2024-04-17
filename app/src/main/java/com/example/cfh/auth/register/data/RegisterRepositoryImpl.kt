package com.example.cfh.auth.register.data

import com.example.cfh.auth.register.domain.repository.RegisterRepository
import kotlinx.coroutines.delay

class RegisterRepositoryImpl : RegisterRepository {


    override suspend fun register(email: String, password: String): Boolean {
        delay(1000)
        return true
    }

}