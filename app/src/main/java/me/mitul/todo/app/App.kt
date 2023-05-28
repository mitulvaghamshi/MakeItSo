package me.mitul.todo.app

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
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
fun MakeItSoApp() = MakeItSoTheme(dynamicColor = false) {
    val appState = rememberAppState()
    Scaffold(
        scaffoldState = appState.scaffoldState,
        snackbarHost = { hostState ->
            SnackbarHost(
                hostState = hostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackBarData ->
                    Snackbar(
                        snackbarData = snackBarData,
                        contentColor = MaterialTheme.colors.onPrimary,
                    )
                }
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
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = LocalContext.current.resources,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(scaffoldState, navController, snackBarManager, resources, coroutineScope) {
    AppState(scaffoldState, navController, snackBarManager, resources, coroutineScope)
}
