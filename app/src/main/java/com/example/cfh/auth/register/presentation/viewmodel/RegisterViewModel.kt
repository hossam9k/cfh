package com.example.cfh.auth.register.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cfh.auth.register.domain.model.RegisterInputValidationType
import com.example.cfh.auth.register.domain.repository.RegisterRepository
import com.example.cfh.auth.register.domain.use_case.ValidateRegisterInputUseCase
import com.example.cfh.auth.register.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val registerRepository: RegisterRepository
) : ViewModel() {

    var registerState by mutableStateOf(RegisterState())
        private set

    fun onFirstnameInputChange(newValue: String) {
        registerState = registerState.copy(firstnameInput = newValue)
        checkInputValidation()
    }

    fun onLastnameInputChange(newValue: String) {
        registerState = registerState.copy(lastnameInput = newValue)
        checkInputValidation()
    }

    fun onAgeInputChange(newValue: String) {
        registerState = registerState.copy(ageInput = newValue)
        checkInputValidation()
    }

    fun onEmailInputChange(newValue: String) {
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformationPassword() {
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }

    fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try {
                val registerResult = registerRepository.register(
                    email = registerState.emailInput,
                    password = registerState.passwordInput
                )
                registerState.copy(isSuccessfullyRegistered = registerResult)
            } catch (e: Exception) {
                registerState.copy(errorMessageRegisterProcess = "Could not login")
            } finally {
                registerState = registerState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            registerState.firstnameInput,
            registerState.lastnameInput,
            registerState.ageInput,
            registerState.emailInput,
            registerState.passwordInput,
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        registerState = when (type) {
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }

            RegisterInputValidationType.NoEmail -> {
                registerState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }

            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(errorMessageInput = "Password too short", isInputValid = false)
            }

            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(
                    errorMessageInput = "Passwords do not match",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(
                    errorMessageInput = "Password needs to contain at least one upper case char",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(
                    errorMessageInput = "Password needs to contain at least one special char",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(
                    errorMessageInput = "Password needs to contain at least one number",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.Valid -> {
                registerState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

}