package me.mitul.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.mitul.todo.app.MakeItSoApp
import me.mitul.todo.common.useEmulators

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useEmulators()
        setContent { MakeItSoApp() }
    }
}
