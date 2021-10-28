package com.example.discover_destination.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.viewmodel.DestinationsViewModel

@Composable
fun DeleteScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    onFinishedOperation: () -> Unit,
    user: String
) {
    val listOfDestinations by remember { mutableStateOf(viewModel.listOfDestinations.value.filter{ destination -> destination.user.equals(user) })}
    val title = "Delete Destinations"

    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            fontSize = 35.sp
        )
        LazyColumn {
            items(listOfDestinations){
                    item: Destination ->
                SingleDestinationItemOnDeleteScreen(
                    destination = item,
                    onDestinationClick = {
                        viewModel.delete(item.city)
                        onFinishedOperation()
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun SingleDestinationItemOnDeleteScreen(
    destination: Destination,
    onDestinationClick: (Destination) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onDestinationClick(destination)
            },
        elevation = 8.dp,
        shape = RectangleShape,
        backgroundColor = Color.LightGray
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.padding(25.dp)
            ) {
                Text(
                    text = destination.city,
                    fontSize = 20.sp
                )
                Text(
                    text = destination.country,
                    fontSize = 15.sp
                )
            }
            Column(
                modifier = Modifier.padding(25.dp)
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = null
                )
            }
        }
    }
}