package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.mitul.todo.extension.filledButton
import me.mitul.todo.extension.textButton
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun BasicButton(@StringRes text: Int, onClick: () -> Unit) = TextButton(
    onClick = onClick,
    modifier = Modifier.textButton()
) {
    Text(text = stringResource(id = text))
}

@Composable
fun FilledButton(@StringRes text: Int, onClick: () -> Unit) = Button(
    onClick = onClick,
    modifier = Modifier.filledButton(),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    )
) {
    Text(text = stringResource(id = text), fontSize = 16.sp)
}

@Composable
fun ConfirmButton(@StringRes text: Int, onClick: () -> Unit) = Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    )
) {
    Text(text = stringResource(id = text))
}

@Composable
fun CancelButton(@StringRes text: Int, onClick: () -> Unit) = Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary,
    )
) {
    Text(text = stringResource(id = text))
}

@Composable
@Preview(showBackground = true)
private fun BasicButton_Preview() = BasicButton(text = AppName) {}

@Composable
@Preview(showBackground = true)
private fun FilledButton_Preview() = FilledButton(text = AppName) {}

@Composable
@Preview(showBackground = true)
private fun ConfirmButton_Preview() = ConfirmButton(text = AppName) {}

@Composable
@Preview(showBackground = true)
private fun CancelButton_Preview() = CancelButton(text = AppName) {}
