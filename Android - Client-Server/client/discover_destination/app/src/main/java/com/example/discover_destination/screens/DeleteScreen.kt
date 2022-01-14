package com.example.discover_destination.screens

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.viewmodel.DestinationsViewModel
import kotlinx.coroutines.launch

@Composable
fun DeleteScreen(
    viewModel: DestinationsViewModel = hiltViewModel(),
    onFinishedOperation: () -> Unit,
    user: String
) {
    val listOfDestinations by viewModel.listOfDestinations.observeAsState(listOf())
    val title = "Delete Destinations"
    val context = LocalContext.current
    val progressIndicatorVisibility = remember{ mutableStateOf(false) }
    val snackbarHostState = remember{mutableStateOf(SnackbarHostState())}
    val snackbarCoroutineScope = rememberCoroutineScope()

    val showMessage: (Boolean, String) -> Unit = { isSuccessful: Boolean, message: String ->
        if(isSuccessful){
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            snackbarCoroutineScope.launch {
                snackbarHostState.value.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    if(progressIndicatorVisibility.value){
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
    viewModel.getAllLocal()

    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            fontSize = 35.sp
        )
        LazyColumn {
            items(listOfDestinations.filter{ destination -> destination.user.equals(user) }){
                    item: Destination ->
                SingleDestinationItemOnDeleteScreen(
                    destination = item,
                    onDestinationClick = {
                        viewModel.delete(context, item, showMessage, progressIndicatorVisibility)
                        viewModel.syncDestinations
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