package me.mitul.todo.app

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.common.SnackBarManager

@Composable
fun MakeItSoApp() = MakeItSoTheme {
    val appState = rememberAppState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackBarHostState,
                modifier = Modifier.padding(all = 8.dp),
                snackbar = { data -> Snackbar(snackbarData = data) }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = appState.navController,
            startDestination = SPLASH_SCREEN,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            navGraph(appState = appState)
        }
    }
}

@Composable
private fun rememberAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = LocalContext.current.resources,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(snackBarHostState, navController, snackBarManager, resources, coroutineScope) {
    AppState(
        snackBarHostState = snackBarHostState,
        navController = navController,
        snackBarManager = snackBarManager,
        resources = resources,
        coroutineScope = coroutineScope
    )
}
