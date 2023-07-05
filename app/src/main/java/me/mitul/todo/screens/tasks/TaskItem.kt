package me.mitul.todo.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.mitul.todo.composable.ContextMenu
import me.mitul.todo.extension.hasDueDate
import me.mitul.todo.extension.hasDueTime
import me.mitul.todo.model.Task
import me.mitul.todo.R.drawable as AppIcon

@Composable
fun TaskItem(
    task: Task,
    options: List<String>,
    onCheckChange: (Task) -> Unit,
    onContextMenuItemClick: (String) -> Unit,
) = Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = task.completed,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
            onCheckedChange = { onCheckChange(task) }
        )
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = getDueDateTime(task = task),
                fontSize = 12.sp
            )
        }
        if (task.flag) {
            Icon(
                painter = painterResource(id = AppIcon.ic_flag),
                contentDescription = "Flag",
                tint = Color(color = 0xFFF57C00),
            )
        }
        ContextMenu(
            options = options,
            onSelect = onContextMenuItemClick
        )
    }
}

private fun getDueDateTime(task: Task): String {
    val builder = StringBuilder()
    if (task.hasDueDate()) builder.append(task.dueDate).append(" ")
    if (task.hasDueTime()) builder.append("at ").append(task.dueTime)
    return builder.toString()
}

@Preview(showBackground = true)
@Composable
private fun TaskItem_Preview() {
    val task = Task(
        id = "1",
        title = "Buy Apples",
        priority = "Low",
        dueDate = "01/01/2023",
        dueTime = "12:35 AM",
        description = "Today's shopping list item...",
        completed = false,
        flag = true,
        url = "https://www.google.com/search?q=apples"
    )
    val options = listOf("Edit item", "Delete item", "Toggle flag")

    TaskItem(
        task = task,
        options = options,
        onCheckChange = {},
        onContextMenuItemClick = {}
    )
}
