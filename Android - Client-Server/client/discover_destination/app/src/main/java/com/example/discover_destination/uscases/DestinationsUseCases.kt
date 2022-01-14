package com.example.discover_destination.uscases

import androidx.compose.runtime.MutableState
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.repository.DestinationsRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DestinationsUseCases @Inject constructor(
    val repo: DestinationsRepositoryInterface
) : DestinationsUseCasesInterface {

    override suspend fun invokeGetAllLocalDestinations(): Flow<List<Destination>> {
        return repo.getAllLocalDestinations()
    }

    override suspend fun invokeLoadAllDestinations(showError: (String) -> Unit, progressIndicatorVisibility: MutableState<Boolean>) {
        return repo.loadAllDestinations(showError, progressIndicatorVisibility)
    }

    override suspend fun invokeAddLocally(destination: Destination) {
        repo.addLocally(destination)
    }

    override suspend fun invokeAdd(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility: MutableState<Boolean>) {
        repo.add(destination, showMessage, progressIndicatorVisibility)
    }

    override suspend fun invokeModify(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility: MutableState<Boolean>
    ) {
        repo.modify(destination, showMessage, progressIndicatorVisibility)
    }

    override suspend fun invokeDelete(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility: MutableState<Boolean>
    ) {
        repo.delete(destination, showMessage, progressIndicatorVisibility)
    }

    override suspend fun invokeSyncDestinations() {
        repo.syncDestinations()
    }

    override suspend fun invokeFilter(country: String, user: String): Flow<List<Destination>> {
        return repo.getByCountry(country, user)
    }

    override suspend fun invokeGetOne(city: String): Destination {
        return repo.getOne(city)
    }

}