package me.mitul.todo.app

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import me.mitul.todo.common.SnackBarManager
import me.mitul.todo.common.SnackBarMessage.Companion.toMessage

@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackBarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope,
) {
    init {
        coroutineScope.launch {
            snackBarManager.snackBarMessages.filterNotNull().collect { snackBarMessage ->
                val text = snackBarMessage.toMessage(resources = resources)
                scaffoldState.snackbarHostState.showSnackbar(message = text)
            }
        }
    }

    fun navigateBack() = navController.popBackStack()

    fun navigateTo(route: String) = navController.navigate(route = route) {
        launchSingleTop = true
    }

    fun replaceWith(route: String, oldRoute: String) = navController.navigate(route = route) {
        launchSingleTop = true
        popUpTo(route = oldRoute) { inclusive = true }
    }

    fun replaceAllWith(route: String) = navController.navigate(route = route) {
        launchSingleTop = true
        popUpTo(id = 0) { inclusive = true }
    }
}
