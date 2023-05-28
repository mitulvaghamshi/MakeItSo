package me.mitul.todo.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.composable.AppBar
import me.mitul.todo.composable.BasicButton
import me.mitul.todo.composable.EmailField
import me.mitul.todo.composable.FilledButton
import me.mitul.todo.composable.PasswordField
import me.mitul.todo.R.string as AppText

@Composable
fun LoginScreen(
    onSuccess: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    LoginScreen(
        email = uiState.email,
        password = uiState.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignIn = { viewModel.trySignIn(onSuccess = onSuccess) },
        onForgot = viewModel::onForgotPasswordClick
    )
}

@Composable
private fun LoginScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onForgot: () -> Unit
) {
    AppBar(title = AppText.login_details)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = email, onChange = onEmailChange)
        PasswordField(value = password, onChange = onPasswordChange)
        FilledButton(text = AppText.sign_in, onClick = onSignIn)
        BasicButton(text = AppText.forgot_password, onClick = onForgot)
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginScreen_Preview() = LoginScreen("", "", {}, {}, {}, {})
