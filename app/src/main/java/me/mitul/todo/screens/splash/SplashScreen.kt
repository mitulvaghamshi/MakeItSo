package me.mitul.todo.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import me.mitul.todo.common.SPLASH_TIMEOUT
import me.mitul.todo.extension.filledButton
import me.mitul.todo.R.string as AppText

@Composable
fun SplashScreen(
    onComplete: (String, String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) = SplashScreen(showError = viewModel.showError.value) {
    viewModel.trySignOn(onComplete = onComplete)
}

@Composable
private fun SplashScreen(showError: Boolean, trySignIn: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (showError) {
            Text(text = stringResource(id = AppText.generic_error))
            Button(
                onClick = trySignIn,
                modifier = Modifier.filledButton(),
            ) {
                Text(
                    text = stringResource(id = AppText.try_again),
                    fontSize = 16.sp
                )
            }
        } else {
            CircularProgressIndicator()
        }
    }
    LaunchedEffect(key1 = Unit) {
        delay(timeMillis = SPLASH_TIMEOUT)
        trySignIn()
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreen_Preview() = SplashScreen(showError = true) {}
