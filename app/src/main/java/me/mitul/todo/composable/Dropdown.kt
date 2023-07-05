package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.mitul.todo.R
import me.mitul.todo.extension.card
import me.mitul.todo.screens.tasks.TaskActionOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownList(
    @StringRes title: Int,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
) = Card(modifier = Modifier.card()) {
    var expanded by remember { mutableStateOf(value = false) }
    TextField(
        readOnly = true,
        value = selected,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = title)) },
        trailingIcon = {
            DropDownMenu(
                options = options,
                expanded = expanded,
                menuToggle = { expanded = !expanded },
                onItemSelect = { option ->
                    expanded = false
                    onSelect(option)
                }) {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }
    )
}

@Composable
fun ContextMenu(options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(value = false) }
    var showDialog by remember { mutableStateOf(value = false) }

    if (showDialog) {
        AlertDialog(
            title = R.string.delete_this_task,
            description = R.string.delete_task_description,
            confirmText = R.string.delete_task,
            onDismiss = { showDialog = false }
        ) {
            showDialog = false
            onSelect(TaskActionOption.DeleteTask.title)
        }
    }

    DropDownMenu(
        options = options,
        expanded = expanded,
        menuToggle = { expanded = !expanded },
        onItemSelect = { option ->
            expanded = false
            if (option == TaskActionOption.DeleteTask.title) {
                showDialog = true
            } else {
                onSelect(option)
            }
        },
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More task actions",
        )
    }
}

@Composable
private fun DropDownMenu(
    options: List<String>,
    expanded: Boolean,
    menuToggle: () -> Unit,
    onItemSelect: (String) -> Unit,
    content: @Composable () -> Unit,
) = Box {
    IconButton(onClick = menuToggle, content = content)
    DropdownMenu(expanded = expanded, onDismissRequest = menuToggle) {
        options.forEach { action ->
            DropdownMenuItem(
                text = { Text(text = action) },
                onClick = { onItemSelect(action) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DropDownList_Preview() = DropdownList(
    title = R.string.app_name,
    options = listOf("Apple", "Banana", "Mango"),
    selected = "Apple",
    onSelect = {},
)
