package me.mitul.todo.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.extension.fieldModifier
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun BasicField(
    @StringRes text: Int,
    value: String,
    onChange: (String) -> Unit,
) = OutlinedTextField(
    value = value,
    singleLine = true,
    onValueChange = onChange,
    modifier = Modifier.fieldModifier(),
    placeholder = { Text(text = stringResource(id = text)) }
)

@Composable
fun EmailField(
    value: String,
    onChange: (String) -> Unit,
) = OutlinedTextField(
    value = value,
    singleLine = true,
    onValueChange = onChange,
    modifier = Modifier.fieldModifier(),
    placeholder = { Text(text = stringResource(id = AppText.email)) },
    leadingIcon = {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email"
        )
    }
)

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
) = PasswordField(
    value = value,
    onChange = onChange,
    placeholder = AppText.password
)

@Composable
fun RepeatPasswordField(
    value: String,
    onChange: (String) -> Unit,
) = PasswordField(
    value = value,
    onChange = onChange,
    placeholder = AppText.repeat_password
)

@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onChange: (String) -> Unit,
) {
    var visible by remember { mutableStateOf(value = false) }
    val icon = painterResource(
        if (visible) AppIcon.ic_visibility_on
        else AppIcon.ic_visibility_off
    )
    val transformation = if (visible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fieldModifier(),
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(text = stringResource(id = placeholder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock"
            )
        },
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldComposable_Preview() = Column(
    modifier = Modifier.padding(all = 16.dp)
) {
    BasicField(text = AppName, value = "A basic text field...") {}
    EmailField(value = "Email") {}
    PasswordField(value = "Password") {}
    RepeatPasswordField(value = "Confirm Password") {}
}
