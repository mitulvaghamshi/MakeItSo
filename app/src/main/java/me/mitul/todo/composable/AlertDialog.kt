package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.mitul.todo.R

@Composable
fun AlertDialog(
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes confirmText: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) = AlertDialog(
    title = { Text(text = stringResource(id = title)) },
    text = { Text(text = stringResource(id = description)) },
    onDismissRequest = onDismiss,
    confirmButton = {
        Button(onClick = onConfirm) {
            Text(text = stringResource(id = confirmText))
        }
    },
    dismissButton = {
        Button(
            onClick = onDismiss,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
)

@Composable
@Preview(showBackground = true)
private fun AlertDialog_Preview() = AlertDialog(
    title = R.string.delete_account_title,
    description = R.string.delete_account_description,
    confirmText = R.string.delete_my_account,
    onDismiss = {},
    onConfirm = {}
)
