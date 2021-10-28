package com.example.discover_destination.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.discover_destination.domain.User
import com.example.discover_destination.viewmodel.UsersViewModel

@Composable
fun LogInScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    onFinishedOperation: (String) -> Unit
) {
    val usernameFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val passwordFieldValue = remember { mutableStateOf(TextFieldValue()) }
    var errorValue = remember { mutableStateOf(TextFieldValue()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Log In Form",
            fontSize = 45.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Username",
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = usernameFieldValue.value,
            onValueChange = { usernameFieldValue.value = it }
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Password",
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = passwordFieldValue.value,
            onValueChange = { passwordFieldValue.value = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedButton(onClick = {
            if (viewModel.checkUser(User(usernameFieldValue.value.text, passwordFieldValue.value.text)))
                onFinishedOperation(usernameFieldValue.value.text)
            else{
                errorValue.value = TextFieldValue("Invalid data!")
            }
        },
            modifier = Modifier.size(278.dp, 55.dp)
        ) {
            Text(
                text = "LOG IN",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        Text(
            text = errorValue.value.text,
            fontSize = 15.sp,
            color = Color.Red
        )
    }
}