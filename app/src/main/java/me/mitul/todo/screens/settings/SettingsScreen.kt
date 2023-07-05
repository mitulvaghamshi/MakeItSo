package me.mitul.todo.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.R
import me.mitul.todo.composable.AlertCard
import me.mitul.todo.composable.EditorCard
import me.mitul.todo.composable.LargeAppBar
import me.mitul.todo.extension.spacer
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
fun SettingsScreen(
    onSignOn: (String) -> Unit,
    onSignOff: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
        .collectAsState(initial = SettingsUiState(isAnonymousAccount = false))
    SettingsScreen(
        onSignIn = { viewModel.onNewLogin(push = onSignOn) },
        onSignUp = { viewModel.onNewSignUp(push = onSignOn) },
        onSignOut = { viewModel.trySignOut(onSuccess = onSignOff) },
        onDelete = { viewModel.tryDeleteMyAccount(onSuccess = onSignOff) },
        isAnonymous = uiState.isAnonymousAccount
    )
}

@Composable
private fun SettingsScreen(
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onSignOut: () -> Unit,
    onDelete: () -> Unit,
    isAnonymous: Boolean,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    LargeAppBar(title = AppText.settings)
    Spacer(modifier = Modifier.spacer())
    if (isAnonymous) {
        EditorCard(
            title = AppText.sign_in,
            icon = AppIcon.ic_sign_in,
            onClick = onSignIn
        )
        EditorCard(
            title = AppText.create_account,
            icon = AppIcon.ic_create_account,
            onClick = onSignUp
        )
    } else {
        AlertCard(
            label = AppText.sign_out,
            icon = AppIcon.ic_exit,
            alertTitle = AppText.sign_out_title,
            alertDescription = AppText.sign_out_description,
            confirmText = R.string.sign_out_title,
            onConfirm = onSignOut
        )
        AlertCard(
            label = AppText.delete_my_account,
            icon = AppIcon.ic_delete_my_account,
            contentColor = MaterialTheme.colorScheme.error,
            alertTitle = AppText.delete_account_title,
            alertDescription = AppText.delete_account_description,
            confirmText = R.string.delete_my_account,
            onConfirm = onDelete
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreen_Preview() = SettingsScreen(
    onSignIn = {},
    onSignUp = {},
    onSignOut = {},
    onDelete = {},
    isAnonymous = false
)

@Preview(showBackground = true)
@Composable
private fun SettingsScreenAnonymous_Preview() = SettingsScreen(
    onSignIn = {},
    onSignUp = {},
    onSignOut = {},
    onDelete = {},
    isAnonymous = true
)
