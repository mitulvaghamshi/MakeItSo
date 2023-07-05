package me.mitul.todo.screens.edit_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.composable.LargeAppbar
import me.mitul.todo.composable.BasicField
import me.mitul.todo.composable.DropdownList
import me.mitul.todo.composable.EditorCard
import me.mitul.todo.extension.spacer
import me.mitul.todo.model.Priority
import me.mitul.todo.model.Task
import java.util.Calendar
import java.util.Date
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
fun EditTaskScreen(
    id: String,
    onSave: () -> Unit,
    viewModel: EditTaskViewModel = hiltViewModel(),
) {
    val task by viewModel.task
    EditTaskScreen(
        task = task,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onUrlChange = viewModel::onUrlChange,
        onDateChange = viewModel::onDateChange,
        onTimeChange = viewModel::onTimeChange,
        onPriorityChange = viewModel::onPriorityChange,
        onFlagChange = viewModel::onFlagChange,
        onDone = { viewModel.onDoneClick(pop = onSave) }
    )
    LaunchedEffect(key1 = Unit) { viewModel.initialize(taskId = id) }
}

@Composable
private fun EditTaskScreen(
    task: Task,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onDateChange: (Int, Int, Int) -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    onPriorityChange: (String) -> Unit,
    onFlagChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    val context: Context = LocalContext.current
    val selectedPriority = Priority
        .getByName(name = task.priority).name
    val selectedFlag = EditFlagOption
        .getByCheckedState(checkedState = task.flag).name
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeAppbar(
            title = AppText.edit_task,
            icon = AppIcon.ic_check,
            onClick = onDone,
        )
        Spacer(modifier = Modifier.spacer())
        BasicField(
            text = AppText.title,
            value = task.title,
            onChange = onTitleChange,
        )
        BasicField(
            text = AppText.description,
            value = task.description,
            onChange = onDescriptionChange
        )
        BasicField(
            text = AppText.url,
            value = task.url,
            onChange = onUrlChange
        )
        Spacer(modifier = Modifier.spacer())
        EditorCard(
            title = AppText.date,
            icon = AppIcon.ic_calendar,
            value = task.dueDate
        ) {
            showDatePicker(context = context, onDateChange = onDateChange)
        }
        EditorCard(
            title = AppText.time,
            icon = AppIcon.ic_clock,
            value = task.dueTime
        ) {
            showTimePicker(context = context, onTimeChange = onTimeChange)
        }
        DropdownList(
            options = Priority.getOptions(),
            title = AppText.priority,
            selected = selectedPriority,
            onSelect = onPriorityChange,
        )
        DropdownList(
            options = EditFlagOption.getOptions(),
            title = AppText.flag,
            selected = selectedFlag,
            onSelect = onFlagChange,
        )
    }
}

private fun showDatePicker(
    context: Context,
    onDateChange: (Int, Int, Int) -> Unit,
) {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    DatePickerDialog(
        context,
        { _, year, month, day -> onDateChange(year, month, day) },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

private fun showTimePicker(
    context: Context,
    onTimeChange: (Int, Int) -> Unit,
) {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    TimePickerDialog(
        context,
        { _, hour, minute -> onTimeChange(hour, minute) },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    ).show()
}

@Preview(showBackground = true)
@Composable
private fun EditTaskScreenPreview() = EditTaskScreen(
    task = Task(),
    onTitleChange = {},
    onDescriptionChange = {},
    onUrlChange = {},
    onDateChange = { _, _, _ -> },
    onTimeChange = { _, _ -> },
    onPriorityChange = {},
    onFlagChange = {},
    onDone = {}
)
