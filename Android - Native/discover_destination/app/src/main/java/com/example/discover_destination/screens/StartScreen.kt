package com.example.discover_destination.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.discover_destination.R

@Composable
fun StartScreen(
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onModifyClick: () -> Unit,
    onAllClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.size(400.dp, 400.dp),
            painter = rememberImagePainter(
                R.drawable.logo5
            ),
            contentDescription = null
        )
        OutlinedButton(
            onClick = onAddClick,
            modifier = Modifier.size(300.dp, 70.dp).padding(10.dp)
        ) {
            Text(
                text = "ADD DESTINATION",
                fontSize = 20.sp,
                color = Color.Black)
        }
        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(300.dp, 70.dp).padding(10.dp)
        ) {
            Text(
                text = "DELETE DESTINATION",
                fontSize = 20.sp,
                color = Color.Black)
        }
        OutlinedButton(
            onClick = onModifyClick,
            modifier = Modifier.size(300.dp, 70.dp).padding(10.dp)
        ) {
            Text(
                text = "MODIFY DESTINATION",
                fontSize = 20.sp,
                color = Color.Black)
        }
        OutlinedButton(
            onClick = onAllClick,
            modifier = Modifier.size(300.dp, 70.dp).padding(10.dp)
        ) {
            Text(
                text = "ALL DESTINATIONS",
                fontSize = 20.sp,
                color = Color.Black)
        }
    }
}