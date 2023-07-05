package me.mitul.todo.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.mitul.todo.common.SnackBarManager
import me.mitul.todo.common.SnackBarMessage.Companion.toSnackBarMessage
import me.mitul.todo.service.LogService

open class TodoViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(
        snackBar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit,
    ) = viewModelScope.launch(
        block = block,
        context = CoroutineExceptionHandler { _, throwable ->
            if (snackBar) {
                SnackBarManager.showMessage(message = throwable.toSnackBarMessage())
            }
            logService.logNonFatalCrash(throwable = throwable)
        },
    )
}
