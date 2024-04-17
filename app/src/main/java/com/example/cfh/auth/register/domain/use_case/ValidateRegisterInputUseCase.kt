package com.example.cfh.auth.register.domain.use_case

import com.example.cfh.auth.register.domain.model.RegisterInputValidationType
import com.example.cfh.util.containsNumber
import com.example.cfh.util.containsSpecialChar
import com.example.cfh.util.containsUpperCase

class ValidateRegisterInputUseCase {
    operator fun invoke(
        firstname: String,
        lastname: String,
        age: String,
        email: String,
        password: String,
    ): RegisterInputValidationType {
        if (firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }

        if (password.count() < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }
        if (!password.containsNumber()) {
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if (!password.containsUpperCase()) {
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if (!password.containsSpecialChar()) {
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid
    }
}