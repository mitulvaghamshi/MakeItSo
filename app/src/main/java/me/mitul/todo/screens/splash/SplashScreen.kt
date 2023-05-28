package me.mitul.todo.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import me.mitul.todo.common.SPLASH_TIMEOUT
import me.mitul.todo.composable.FilledButton
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (showError) {
            Text(text = stringResource(id = AppText.generic_error))
            FilledButton(text = AppText.try_again, onClick = trySignIn)
        } else {
            CircularProgressIndicator()
        }
    }
    LaunchedEffect(true) {
        delay(timeMillis = SPLASH_TIMEOUT)
        trySignIn()
    }
}

@Composable
@Preview(showBackground = true)
private fun SplashScreen_Preview() = SplashScreen(showError = true) {}
