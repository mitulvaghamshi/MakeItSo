package me.mitul.todo.screens.edit_task

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import me.mitul.todo.composable.Appbar
import me.mitul.todo.composable.BasicField
import me.mitul.todo.composable.DropdownList
import me.mitul.todo.composable.EditorCard
import me.mitul.todo.extension.spacer
import me.mitul.todo.model.Priority
import me.mitul.todo.model.Task
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun EditTaskScreen(
    id: String,
    onComplete: () -> Unit,
    viewModel: EditTaskViewModel = hiltViewModel(),
) {
    val task by viewModel.task
    LaunchedEffect(key1 = Unit) { viewModel.initialize(taskId = id) }
    EditTaskScreen(
        task = task,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onUrlChange = viewModel::onUrlChange,
        onDateChange = viewModel::onDateChange,
        onTimeChange = viewModel::onTimeChange,
        onPriorityChange = viewModel::onPriorityChange,
        onFlagChange = viewModel::onFlagChange,
        onDone = { viewModel.onDoneClick(pop = onComplete) }
    )
}

@Composable
@ExperimentalMaterialApi
private fun EditTaskScreen(
    task: Task,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    onPriorityChange: (String) -> Unit,
    onFlagChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    val context: Context = LocalContext.current
    val selectedPriority = Priority.getByName(name = task.priority).name
    val selectedFlag = EditFlagOption.getByCheckedState(checkedState = task.flag).name
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(
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
            title = AppText.priority,
            options = Priority.getOptions(),
            selected = selectedPriority,
            onSelect = onPriorityChange
        )
        DropdownList(
            title = AppText.flag,
            options = EditFlagOption.getOptions(),
            selected = selectedFlag,
            onSelect = onFlagChange
        )
    }
}

private fun showDatePicker(context: Context?, onDateChange: (Long) -> Unit) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    (context as AppCompatActivity).let {
        picker.show(/* manager = */ it.supportFragmentManager, /* tag = */ picker.toString())
        picker.addOnPositiveButtonClickListener { timeInMillis -> onDateChange(timeInMillis) }
    }
}

private fun showTimePicker(context: Context, onTimeChange: (Int, Int) -> Unit) {
    val picker = MaterialTimePicker.Builder().setTimeFormat(/* format = */ TimeFormat.CLOCK_24H)
        .build()
    (context as AppCompatActivity).let {
        picker.show(/* manager = */it.supportFragmentManager,/* tag = */picker.toString())
        picker.addOnPositiveButtonClickListener { onTimeChange(picker.hour, picker.minute) }
    }
}

@Composable
@Preview(showBackground = true)
@OptIn(ExperimentalMaterialApi::class)
private fun EditTaskScreenPreview() =
    EditTaskScreen(Task(), {}, {}, {}, {}, { _, _ -> }, {}, {}, {})
