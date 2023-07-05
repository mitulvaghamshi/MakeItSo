package me.mitul.todo.extension

import me.mitul.todo.model.Task

fun Task?.hasDueDate(): Boolean = this?.dueDate
    .orEmpty()
    .isNotBlank()

fun Task?.hasDueTime(): Boolean = this?.dueTime
    .orEmpty()
    .isNotBlank()
