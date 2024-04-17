package com.example.cfh.auth.register.presentation.state

data class RegisterState(
    val firstnameInput: String = "",
    val lastnameInput: String = "",
    val ageInput: String = "",
    val emailInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)
