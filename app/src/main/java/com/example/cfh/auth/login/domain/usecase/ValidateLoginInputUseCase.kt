package com.example.cfh.auth.login.domain.usecase

import com.example.cfh.auth.login.domain.model.LoginInputValidationType

class ValidateLoginInputUseCase {

    operator fun invoke(email: String, password: String): LoginInputValidationType {
        if (email.isEmpty() || password.isEmpty()) {
            return LoginInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}