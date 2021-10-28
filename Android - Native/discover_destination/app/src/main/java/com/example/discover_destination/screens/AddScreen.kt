package com.example.discover_destination.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
fun AddScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    onFinishedOperation: () -> Unit,
    user: String
) {
    val cityFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val countryFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val tourismFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val imageFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val descriptionFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val attractionFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val attractionsValue: MutableState<String> = remember { mutableStateOf("") }
    val errorValue = remember { mutableStateOf(TextFieldValue()) }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
                text = "Add Destination",
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
                text = "Tourist Attraction",
                fontSize = 15.sp
            )
            Row(){
                OutlinedTextField(
                    value = attractionFieldValue.value,
                    onValueChange = { attractionFieldValue.value = it }
                )
                OutlinedButton(onClick = {
                    attractionsValue.value += attractionFieldValue.value.text + ", "
                    attractionFieldValue.value = TextFieldValue() },
                    modifier = Modifier.size(55.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "List of Tourist Attractions:",
                fontSize = 15.sp
            )
            Text(
                text = attractionsValue.value,
                fontSize = 20.sp
            )
            OutlinedButton(onClick = {
                val destination = Destination(cityFieldValue.value.text, countryFieldValue.value.text,
                    tourismFieldValue.value.text, descriptionFieldValue.value.text,
                    imageFieldValue.value.text, attractionsValue.value.split(", ").dropLast(1), user)
                if (validate(destination)){
                    viewModel.add(destination)
                    onFinishedOperation()
                }
                else {
                    errorValue.value = TextFieldValue("Invalid data!")
                }
            },
                modifier = Modifier.size(400.dp, 55.dp)
            ) {
                Text(
                    text = "ADD DESTINATION",
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

fun validate(destination: Destination): Boolean{
    return destination.city.length > 0 && destination.country.length > 0 &&
            destination.description.length > 0 && destination.image.length > 0 &&
            destination.tourism.length > 0 && !destination.touristAttractions.isEmpty()
}