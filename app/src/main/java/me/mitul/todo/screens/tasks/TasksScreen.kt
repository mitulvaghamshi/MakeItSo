package me.mitul.todo.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.mitul.todo.composable.LargeAppbar
import me.mitul.todo.extension.smallSpacer
import me.mitul.todo.model.Task
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
fun TasksScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
    val options by viewModel.options
    TasksScreen(
        tasks = tasks.value,
        options = options,
        onCheckChange = viewModel::onTaskCheckChange,
        onSettingsClick = { viewModel.onSettingsClick(push = onNavigate) },
        onAddItemClick = { viewModel.onAddClick(push = onNavigate) }
    ) { action, task ->
        viewModel.onTaskActionClick(push = onNavigate, task = task, action = action)
    }
    LaunchedEffect(key1 = viewModel) { viewModel.loadTaskOptions() }
}

@Composable
private fun TasksScreen(
    tasks: List<Task>,
    options: List<String>,
    onCheckChange: (Task) -> Unit,
    onSettingsClick: () -> Unit,
    onAddItemClick: () -> Unit,
    onContextMenuItemClick: (String, Task) -> Unit,
) = Scaffold(floatingActionButton = {
    FloatingActionButton(onClick = onAddItemClick) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Task"
        )
    }
}) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        LargeAppbar(
            title = AppText.tasks,
            icon = AppIcon.ic_settings,
            onClick = onSettingsClick
        )
        Spacer(modifier = Modifier.smallSpacer())
        LazyColumn {
            items(items = tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    options = options,
                    onCheckChange = onCheckChange
                ) { action ->
                    onContextMenuItemClick(action, task)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TasksScreen_Preview() {
    val tasks = List(size = 5) { index ->
        Task(
            id = index.toString(),
            title = "Buy Apples",
            priority = "Low",
            dueDate = "${index + 1}/01/2023",
            dueTime = "1$index:3$index AM",
            description = "Today's shopping list item...",
            completed = index % 2 == 0,
            flag = index % 3 == 0,
            url = "https://www.google.com/search?q=apples"
        )
    }
    val options = TaskActionOption.values().map { it.title }

    TasksScreen(
        tasks = tasks,
        options = options,
        onCheckChange = {},
        onSettingsClick = {},
        onAddItemClick = {},
        onContextMenuItemClick = { _, _ -> }
    )
}
