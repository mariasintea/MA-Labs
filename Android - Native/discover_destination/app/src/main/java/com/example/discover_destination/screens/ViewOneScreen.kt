package com.example.discover_destination.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.discover_destination.viewmodel.DestinationsViewModel

@Composable
fun ViewOneScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    city: String
) {
    val destination = viewModel.getOne(city)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
    {
        Image(
            modifier = Modifier.size(400.dp, 300.dp),
            painter = rememberImagePainter(
                destination.image
            ),
            contentDescription = null
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(){
                Text(
                    text = destination.city,
                    fontSize = 40.sp
                )
                Text(
                    text = destination.country,
                    fontSize = 30.sp
                )
            }
            Text(
                text = destination.tourism,

                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = destination.description,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "List of tourist attractions:",
            fontSize = 30.sp
        )
        LazyColumn {
            items(destination.touristAttractions){
                    item: String ->
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = item,
                        fontSize = 25.sp
                    )
                }

            }
        }
    }
}