package me.mitul.todo.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.extension.card
import me.mitul.todo.R.drawable.ic_check as AppIcon
import me.mitul.todo.R.string.app_name as AppName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorCard(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    value: String? = "",
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit,
) = Card(modifier = Modifier.card(), onClick = onClick) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(weight = 1f)) {
            Text(text = stringResource(id = title), color = contentColor)
        }
        if (!value.isNullOrEmpty()) {
            Text(
                text = value,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
            )
        }
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            tint = contentColor,
        )
    }
}

@Composable
fun AlertCard(
    @StringRes label: Int,
    @DrawableRes icon: Int,
    @StringRes alertTitle: Int,
    @StringRes alertDescription: Int,
    @StringRes confirmText: Int,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onConfirm: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    EditorCard(title = label, icon = icon, contentColor = contentColor) {
        showDialog = true
    }
    if (showDialog) {
        AlertDialog(
            title = alertTitle,
            description = alertDescription,
            confirmText = confirmText,
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                onConfirm()
            }
        )
    }
}

@Preview
@Composable
private fun EditorCard_Preview() = EditorCard(
    title = AppName,
    icon = AppIcon,
    value = "This is basic Card",
) {}
