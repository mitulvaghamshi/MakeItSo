package me.mitul.todo.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.common.TASKS_SCREEN
import me.mitul.todo.screens.TodoViewModel
import me.mitul.todo.service.AccountService
import me.mitul.todo.service.ConfigurationService
import me.mitul.todo.service.LogService
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService,
) : TodoViewModel(logService = logService) {
    val showError = mutableStateOf(value = false)

    init {
        launchCatching {
            configurationService.fetchConfiguration()
        }
    }

    fun trySignOn(onComplete: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) {
            onComplete(TASKS_SCREEN, SPLASH_SCREEN)
        } else {
            createAnonymousAccount(onComplete = onComplete)
        }
    }

    private fun createAnonymousAccount(onComplete: (String, String) -> Unit) {
        launchCatching(snackBar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            onComplete(TASKS_SCREEN, SPLASH_SCREEN)
        }
    }
}
