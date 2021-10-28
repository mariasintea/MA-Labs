package com.example.discover_destination.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.viewmodel.DestinationsViewModel

@Composable
fun EditScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    user: String,
    city: String,
    onFinishedOperation: () -> Unit
){
    val destination = viewModel.getOne(city)
    val cityFieldValue = remember { mutableStateOf(TextFieldValue(destination.city)) }
    val countryFieldValue = remember { mutableStateOf(TextFieldValue(destination.country)) }
    val tourismFieldValue = remember { mutableStateOf(TextFieldValue(destination.tourism)) }
    val imageFieldValue = remember { mutableStateOf(TextFieldValue(destination.image)) }
    val descriptionFieldValue = remember { mutableStateOf(TextFieldValue(destination.description)) }
    val attractionsFieldValue = remember { mutableStateOf(TextFieldValue(destination.touristAttractions.reduce{acc: String, s: String -> acc + "\n" + s})) }
    var errorValue = remember { mutableStateOf(TextFieldValue())}

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Edit Destination",
            fontSize = 35.sp
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "City",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = cityFieldValue.value,
                onValueChange = { cityFieldValue.value = it }
            )
            Text(
                text = "Country",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = countryFieldValue.value,
                onValueChange = { countryFieldValue.value = it }
            )
            Text(
                text = "Type of Tourism",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = tourismFieldValue.value,
                onValueChange = { tourismFieldValue.value = it }
            )
            Text(
                text = "Image URL",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = imageFieldValue.value,
                onValueChange = { imageFieldValue.value = it }
            )
            Text(
                text = "Description",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = descriptionFieldValue.value,
                onValueChange = { descriptionFieldValue.value = it }
            )
            Text(
                text = "List of Tourist Attractions:",
                fontSize = 15.sp
            )
            OutlinedTextField(
                value = attractionsFieldValue.value,
                onValueChange = { attractionsFieldValue.value = it }
            )
            OutlinedButton(onClick = {
                val destination = Destination(cityFieldValue.value.text, countryFieldValue.value.text,
                    tourismFieldValue.value.text, descriptionFieldValue.value.text,
                    imageFieldValue.value.text, attractionsFieldValue.value.text.split("\n"), user)
                if (validate(destination)){
                    viewModel.modify(destination)
                    onFinishedOperation()
                }
                else {
                    errorValue.value = TextFieldValue("Invalid data!")
                }
            },
                modifier = Modifier.size(400.dp, 55.dp)
            ) {
                Text(
                    text = "MODIFY DESTINATION",
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
}