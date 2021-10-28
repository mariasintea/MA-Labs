package com.example.discover_destination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.discover_destination.navigation.DestinationsNavigation
import com.example.discover_destination.ui.theme.Discover_destinationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Discover_destinationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    DiscoverDestinationApp()
                }
            }
        }
    }
}

@Composable
fun DiscoverDestinationApp(){
    DestinationsNavigation()
}