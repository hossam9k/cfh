package com.example.cfh.auth.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cfh.auth.register.presentation.viewmodel.RegisterViewModel
import com.example.cfh.core.components.AuthButton
import com.example.cfh.core.components.HeaderBackground
import com.example.cfh.core.components.NavDestinationHelper
import com.example.cfh.core.components.TextEntryModule
import com.example.cfh.ui.theme.gray
import com.example.cfh.ui.theme.orange
import com.example.cfh.ui.theme.white
import com.example.cfh.ui.theme.whiteGray
import com.example.cfh.ui.theme.whiteGrayOrange
import com.example.cfh.util.PreferencesManager

@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }

    NavDestinationHelper(
        shouldNavigate = {
            preferencesManager.saveData("firstname", registerViewModel.registerState.firstnameInput)
            preferencesManager.saveData("lastname", registerViewModel.registerState.lastnameInput)
            preferencesManager.saveData("age", registerViewModel.registerState.ageInput)
            preferencesManager.saveData("email", registerViewModel.registerState.emailInput)

            registerViewModel.registerState.isSuccessfullyRegistered
        },
        destination = {
            onRegisterSuccessNavigation()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Register",
                style = MaterialTheme.typography.h4,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        RegisterContainer(
            firstnameValue = {
                registerViewModel.registerState.firstnameInput
            },
            lastnameValue = {
                registerViewModel.registerState.lastnameInput
            },
            ageValue = {
                registerViewModel.registerState.ageInput
            },
            emailValue = {
                registerViewModel.registerState.emailInput
            },
            passwordValue = {
                registerViewModel.registerState.passwordInput
            },
            buttonEnabled = {
                registerViewModel.registerState.isInputValid
            },
            onFirstnameChanged = registerViewModel::onFirstnameInputChange,
            onLastnameChanged = registerViewModel::onLastnameInputChange,
            onAgeChanged = registerViewModel::onAgeInputChange,
            onEmailChanged = registerViewModel::onEmailInputChange,
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            onButtonClick = registerViewModel::onRegisterClick,
            isPasswordShown = {
                registerViewModel.registerState.isPasswordShown
            },
            onTrailingPasswordIconClick = {
                registerViewModel.onToggleVisualTransformationPassword()
            },
            errorHint = {
                registerViewModel.registerState.errorMessageInput
            },
            isLoading = {
                registerViewModel.registerState.isLoading
            },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Already have an account?",
                style = MaterialTheme.typography.body2,
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToLoginScreen()
                    },
                text = "Login",
                color = orange,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun RegisterContainer(
    firstnameValue: () -> String,
    lastnameValue: () -> String,
    ageValue: () -> String,
    emailValue: () -> String,
    passwordValue: () -> String,
    buttonEnabled: () -> Boolean,
    onFirstnameChanged: (String) -> Unit,
    onLastnameChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "First name",
            hint = "First name",
            leadingIcon = Icons.Default.Person,
            textValue = firstnameValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onFirstnameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Last name",
            hint = "Last name",
            leadingIcon = Icons.Default.Person,
            textValue = lastnameValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onLastnameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Age",
            hint = "Age",
            leadingIcon = Icons.Default.PedalBike,
            textValue = ageValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onAgeChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            keyboardType = KeyboardType.Number
        )

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "KApps@gmail.com",
            leadingIcon = Icons.Default.Email,
            textValue = emailValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password",
            hint = "Enter Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isPasswordShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Register",
                backgroundColor = orange,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                onButtonClick = onButtonClick,
                isLoading = isLoading()
            )
            Text(
                errorHint() ?: "",
                style = MaterialTheme.typography.caption
            )
        }
    }
}