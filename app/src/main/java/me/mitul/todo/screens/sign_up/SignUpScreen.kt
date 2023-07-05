package me.mitul.todo.screens.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.composable.LargeAppBar
import me.mitul.todo.composable.EmailField
import me.mitul.todo.composable.PasswordField
import me.mitul.todo.composable.RepeatPasswordField
import me.mitul.todo.extension.filledButton
import me.mitul.todo.R.string as AppText

@Composable
fun SignUpScreen(
    onSuccess: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState
    SignUpScreen(
        email = state.email,
        password = state.password,
        repeatPassword = state.repeatPassword,
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
    LargeAppBar(title = AppText.create_account)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        EmailField(
            value = email,
            onChange = onEmailChange
        )
        PasswordField(
            value = password,
            onChange = onPasswordChange
        )
        RepeatPasswordField(
            value = repeatPassword,
            onChange = onRepeatPasswordChange
        )
        Button(
            onClick = onSignUp,
            modifier = Modifier.filledButton(),
        ) {
            Text(
                text = stringResource(id = AppText.create_account),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreen_Preview() = SignUpScreen(
    email = "",
    password = "",
    repeatPassword = "",
    onEmailChange = {},
    onPasswordChange = {},
    onRepeatPasswordChange = {},
    onSignUp = {}
)
