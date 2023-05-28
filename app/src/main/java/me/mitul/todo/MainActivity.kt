package me.mitul.todo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import me.mitul.todo.app.MakeItSoApp
import me.mitul.todo.common.Emulator

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Emulator.useIfDebug()
        setContent { MakeItSoApp() }
    }
}
