package me.mitul.todo.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.mitul.todo.extension.toolbarActions
import me.mitul.todo.R.drawable.ic_settings as AppSetting
import me.mitul.todo.R.string.app_name as AppName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeAppBar(@StringRes title: Int) = LargeTopAppBar(
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    title = { Text(text = stringResource(id = title)) }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeAppbar(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) = LargeTopAppBar(
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    title = { Text(text = stringResource(id = title)) },
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
private fun AppBar_Preview() = LargeAppBar(title = AppName)

@Preview
@Composable
private fun AppBarWithAction_Preview() = LargeAppbar(title = AppName, icon = AppSetting) {}
