package com.example.discover_destination.repository

import androidx.compose.runtime.MutableState
import com.example.discover_destination.domain.Destination
import kotlinx.coroutines.flow.Flow

interface DestinationsRepositoryInterface {
    suspend fun getAllLocalDestinations(): Flow<List<Destination>>
    suspend fun loadAllDestinations(showError: (String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun addLocally(destination: Destination)
    suspend fun add(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun modify(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun delete(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun syncDestinations()
    suspend fun getByCountry(country: String, user: String): Flow<List<Destination>>
    suspend fun getOne(city: String): Destination
}