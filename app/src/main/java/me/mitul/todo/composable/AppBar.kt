package me.mitul.todo.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.mitul.todo.extension.toolbarActions
import me.mitul.todo.R.drawable.ic_settings as AppSetting
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun AppBar(@StringRes title: Int) = TopAppBar(title = {
    Text(stringResource(title))
})

@Composable
fun Appbar(@StringRes title: Int, @DrawableRes icon: Int, onClick: () -> Unit) = TopAppBar(
    title = { Text(stringResource(title)) },
    actions = {
        Box(modifier = Modifier.toolbarActions()) {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Action",
                )
            }
        }
    }
)

@Preview
@Composable
private fun AppBar_Preview() = AppBar(title = AppName)

@Preview
@Composable
private fun AppBarWithAction_Preview() = Appbar(title = AppName, icon = AppSetting) {}
