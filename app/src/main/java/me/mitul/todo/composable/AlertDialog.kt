package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.material.Text
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
) = androidx.compose.material.AlertDialog(
    title = { Text(text = stringResource(id = title)) },
    text = { Text(text = stringResource(id = description)) },
    onDismissRequest = onDismiss,
    dismissButton = {
        CancelButton(text = R.string.cancel, onClick = onDismiss)
    },
    confirmButton = {
        ConfirmButton(text = confirmText, onClick = onConfirm)
    }
)

@Composable
@Preview(showBackground = true)
private fun AlertDialog_Preview() = AlertDialog(
    title = R.string.delete_account_title,
    description = R.string.delete_account_description,
    confirmText = R.string.delete_my_account,
    onDismiss = {}
) {}
