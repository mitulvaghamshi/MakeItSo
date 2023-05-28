package me.mitul.todo.app

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.mitul.todo.common.EDIT_TASK_SCREEN
import me.mitul.todo.common.LOGIN_SCREEN
import me.mitul.todo.common.SETTINGS_SCREEN
import me.mitul.todo.common.SIGN_UP_SCREEN
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.common.TASKS_SCREEN
import me.mitul.todo.common.TASK_DEFAULT_ID
import me.mitul.todo.common.TASK_ID
import me.mitul.todo.common.TASK_ID_ARG
import me.mitul.todo.screens.edit_task.EditTaskScreen
import me.mitul.todo.screens.login.LoginScreen
import me.mitul.todo.screens.settings.SettingsScreen
import me.mitul.todo.screens.sign_up.SignUpScreen
import me.mitul.todo.screens.splash.SplashScreen
import me.mitul.todo.screens.tasks.TasksScreen

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.navGraph(appState: AppState) {
    composable(route = SPLASH_SCREEN) {
        SplashScreen(onComplete = { route, oldRoute ->
            appState.replaceWith(route = route, oldRoute = oldRoute)
        })
    }
    composable(route = LOGIN_SCREEN) {
        LoginScreen(onSuccess = { route, oldRoute ->
            appState.replaceWith(route = route, oldRoute = oldRoute)
        })
    }
    composable(route = SIGN_UP_SCREEN) {
        SignUpScreen(onSuccess = { route, oldRoute ->
            appState.replaceWith(route = route, oldRoute = oldRoute)
        })
    }
    composable(route = TASKS_SCREEN) {
        TasksScreen(onNavigate = { route ->
            appState.navigateTo(route = route)
        })
    }
    composable(route = SETTINGS_SCREEN) {
        SettingsScreen(onSignOn = { route ->
            appState.navigateTo(route = route)
        }, onSignOff = { route ->
            appState.replaceAllWith(route = route)
        })
    }
    composable(
        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(name = TASK_ID) {
            defaultValue = TASK_DEFAULT_ID
        })
    ) {
        EditTaskScreen(
            onComplete = { appState.navigateBack() },
            id = it.arguments?.getString(TASK_ID) ?: TASK_DEFAULT_ID
        )
    }
}
