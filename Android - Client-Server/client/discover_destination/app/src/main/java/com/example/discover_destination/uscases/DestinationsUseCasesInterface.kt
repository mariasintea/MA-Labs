package com.example.discover_destination.uscases

import androidx.compose.runtime.MutableState
import com.example.discover_destination.domain.Destination
import kotlinx.coroutines.flow.Flow

interface DestinationsUseCasesInterface {
    suspend fun invokeGetAllLocalDestinations(): Flow<List<Destination>>
    suspend fun invokeLoadAllDestinations(showError: (String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun invokeAddLocally(destination: Destination)
    suspend fun invokeAdd(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun invokeModify(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun invokeDelete(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>)
    suspend fun invokeSyncDestinations()
    suspend fun invokeFilter(country: String, user: String): Flow<List<Destination>>
    suspend fun invokeGetOne(city: String): Destination
}