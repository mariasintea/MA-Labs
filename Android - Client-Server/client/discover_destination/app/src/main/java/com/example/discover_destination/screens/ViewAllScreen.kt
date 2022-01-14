package com.example.discover_destination.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.viewmodel.DestinationsViewModel
import kotlinx.coroutines.launch

internal var shouldGetListFromServer = true

@Composable
fun ViewAllScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    onDestinationClick: (String) -> Unit,
    user: String
) {
    val listOfDestinations by viewModel.listOfDestinations.observeAsState(listOf())
    val title = "Destinations"
    val searchFieldValue = remember { mutableStateOf(TextFieldValue("Search by country")) }
    val progressIndicatorVisibility = remember{ mutableStateOf(false) }
    val context = LocalContext.current

    val snackbarHostState = remember{mutableStateOf(SnackbarHostState())}
    val snackbarCoroutineScope = rememberCoroutineScope()

    val showError: (String) -> Unit = {
        snackbarCoroutineScope.launch {
            snackbarHostState.value.showSnackbar(
                message = it,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    if (shouldGetListFromServer) {
        viewModel.getAllLocal()
        viewModel.loadDestinations(context, showError, progressIndicatorVisibility)
        shouldGetListFromServer = false
    }
    else
        viewModel.getAllLocal()

    viewModel.isRetrievedSuccessfully.observe(LocalLifecycleOwner.current) {
        if (it == false) {
            Toast.makeText(
                context,
                "There was a problem in retrieving the destinations!",
                Toast.LENGTH_LONG
            ).show()
        }
        else
            viewModel.getAllLocal()
    }

    if(progressIndicatorVisibility.value){
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
    else {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = title,
                fontSize = 45.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
            ) {
                OutlinedTextField(
                    value = searchFieldValue.value,
                    onValueChange = {
                        searchFieldValue.value = it
                    }
                )
                Button(
                    onClick = { /*viewModel.filter(searchFieldValue.value.text, user)*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier.size(55.dp)
                ) {
                    Icon(Icons.Filled.Search, contentDescription = null, tint = Black)
                }
            }
            LazyColumn {
                items(listOfDestinations.filter{d -> d.user == user}) { item: Destination ->
                    SingleDestinationItem(
                        destination = item,
                        onDestinationClick = onDestinationClick
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun SingleDestinationItem(
    destination: Destination,
    onDestinationClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onDestinationClick(destination.city)
            },
        elevation = 8.dp,
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier.padding(25.dp)
        ) {
            Image(
                modifier = Modifier.size(300.dp, 200.dp),
                painter = rememberImagePainter(
                    destination.image
                ),
                contentDescription = null
            )
            Text(
                text = destination.city,
                fontSize = 30.sp
            )
            Text(
                text = destination.country,
                fontSize = 20.sp
            )
        }
    }
}
