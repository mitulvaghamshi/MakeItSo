package me.mitul.todo.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.composable.EmailField
import me.mitul.todo.composable.LargeAppBar
import me.mitul.todo.composable.PasswordField
import me.mitul.todo.extension.filledButton
import me.mitul.todo.extension.textButton
import me.mitul.todo.R.string as AppText

@Composable
fun LoginScreen(
    onSuccess: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState
    LoginScreen(
        email = state.email,
        password = state.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onForgot = viewModel::onForgotPasswordClick,
    ) {
        viewModel.trySignIn(onSuccess = onSuccess)
    }
}

@Composable
private fun LoginScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgot: () -> Unit,
    onSignIn: () -> Unit,
) {
    LargeAppBar(title = AppText.login_details)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = email, onChange = onEmailChange)
        PasswordField(value = password, onChange = onPasswordChange)
        Button(
            onClick = onSignIn,
            modifier = Modifier.filledButton(),
        ) {
            Text(text = stringResource(id = AppText.sign_in), fontSize = 16.sp)
        }
        TextButton(
            onClick = onForgot,
            modifier = Modifier.textButton()
        ) {
            Text(text = stringResource(id = AppText.forgot_password))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreen_Preview() = LoginScreen(
    email = "",
    password = "",
    onEmailChange = {},
    onPasswordChange = {},
    onSignIn = {},
    onForgot = {}
)
