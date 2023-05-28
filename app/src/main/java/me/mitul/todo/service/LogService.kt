package me.mitul.todo.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
