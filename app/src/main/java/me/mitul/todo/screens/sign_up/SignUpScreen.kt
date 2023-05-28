package me.mitul.todo.screens.sign_up

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
import me.mitul.todo.composable.EmailField
import me.mitul.todo.composable.FilledButton
import me.mitul.todo.composable.PasswordField
import me.mitul.todo.composable.RepeatPasswordField
import me.mitul.todo.R.string as AppText

@Composable
fun SignUpScreen(
    onSuccess: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    SignUpScreen(
        email = uiState.email,
        password = uiState.password,
        repeatPassword = uiState.repeatPassword,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange
    ) {
        viewModel.trySignUp(onSuccess = onSuccess)
    }
}

@Composable
private fun SignUpScreen(
    email: String,
    password: String,
    repeatPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
) {
    AppBar(title = AppText.create_account)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = email, onChange = onEmailChange)
        PasswordField(value = password, onChange = onPasswordChange)
        RepeatPasswordField(value = repeatPassword, onChange = onRepeatPasswordChange)
        FilledButton(text = AppText.create_account, onClick = onSignUp)
    }
}

@Composable
@Preview(showBackground = true)
private fun SignUpScreen_Preview() = SignUpScreen("", "", "", {}, {}, {}, {})
