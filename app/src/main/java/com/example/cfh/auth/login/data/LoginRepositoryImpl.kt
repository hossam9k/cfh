package com.example.cfh.auth.login.data

import com.example.cfh.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.delay

class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(email: String, password: String): Boolean {
        delay(1000)
        return true
    }
}