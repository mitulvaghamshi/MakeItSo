package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.R
import me.mitul.todo.extension.card
import me.mitul.todo.extension.contextMenu
import me.mitul.todo.extension.dropdownSelector
import me.mitul.todo.screens.tasks.TaskActionOption

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun DropdownList(
    @StringRes title: Int,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
) = Card(modifier = Modifier.card()) {
    var expanded by remember { mutableStateOf(value = false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = Modifier.dropdownSelector(),
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selected,
            onValueChange = {},
            colors = dropdownColors(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = title)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onSelect(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ContextMenu(actions: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(value = false) }
    var showDialog by remember { mutableStateOf(value = false) }

    if (showDialog) {
        AlertDialog(
            title = R.string.delete_this_task,
            description = R.string.delete_task_description,
            confirmText = R.string.delete_task,
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                onSelect(TaskActionOption.DeleteTask.title)
            }
        )
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = Modifier.contextMenu(),
        onExpandedChange = { expanded = !expanded }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.width(180.dp),
            onDismissRequest = { expanded = false }
        ) {
            actions.forEach { action ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    if (action == TaskActionOption.DeleteTask.title) {
                        showDialog = true
                    } else {
                        onSelect(action)
                    }
                }) {
                    Text(text = action)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun dropdownColors(): TextFieldColors = ExposedDropdownMenuDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.onPrimary,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    trailingIconColor = MaterialTheme.colors.onSurface,
    focusedTrailingIconColor = MaterialTheme.colors.onSurface,
    focusedLabelColor = MaterialTheme.colors.primary,
    unfocusedLabelColor = MaterialTheme.colors.primary
)

@Composable
@Preview(showBackground = true)
private fun DropDownList_Preview() = DropdownList(
    title = R.string.app_name,
    options = listOf("Apple", "Banana", "Mango"),
    selected = "Apple"
) {}
