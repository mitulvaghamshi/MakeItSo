package me.mitul.todo.common

import android.content.res.Resources
import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.mitul.todo.R.string as AppText

object SnackBarManager {
    private val messages: MutableStateFlow<SnackBarMessage?> = MutableStateFlow(value = null)

    val snackBarMessages: StateFlow<SnackBarMessage?> get() = messages.asStateFlow()

    fun showMessage(@StringRes message: Int) {
        messages.value = SnackBarMessage.ResourceSnackBar(message = message)
    }

    fun showMessage(message: SnackBarMessage) {
        messages.value = message
    }
}

sealed class SnackBarMessage {
    class StringSnackBar(val message: String) : SnackBarMessage()

    class ResourceSnackBar(@StringRes val message: Int) : SnackBarMessage()

    companion object {
        fun SnackBarMessage.toMessage(resources: Resources): String = when (this) {
            is StringSnackBar -> this.message
            is ResourceSnackBar -> resources.getString(this.message)
        }

        fun Throwable.toSnackBarMessage(): SnackBarMessage {
            val message = this.message.orEmpty()
            return if (message.isNotBlank()) {
                StringSnackBar(message = message)
            } else {
                ResourceSnackBar(message = AppText.generic_error)
            }
        }
    }
}
