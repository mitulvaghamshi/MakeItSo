package me.mitul.todo.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import me.mitul.todo.common.LOGIN_SCREEN
import me.mitul.todo.common.SIGN_UP_SCREEN
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.service.AccountService
import me.mitul.todo.service.LogService
import me.mitul.todo.service.StorageService
import me.mitul.todo.screens.TodoViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: StorageService,
) : TodoViewModel(logService) {
    val uiState = accountService.currentUser.map {
        SettingsUiState(isAnonymousAccount = it.isAnonymous)
    }

    fun onNewLogin(push: (String) -> Unit) = push(LOGIN_SCREEN)

    fun onNewSignUp(push: (String) -> Unit) = push(SIGN_UP_SCREEN)

    fun trySignOut(onSuccess: (String) -> Unit) = launchCatching {
        accountService.signOut()
        onSuccess(SPLASH_SCREEN)
    }

    fun tryDeleteMyAccount(onSuccess: (String) -> Unit) = launchCatching {
        storageService.deleteAllForUser(userId = accountService.currentUserId)
        accountService.deleteAccount()
        onSuccess(SPLASH_SCREEN)
    }
}
