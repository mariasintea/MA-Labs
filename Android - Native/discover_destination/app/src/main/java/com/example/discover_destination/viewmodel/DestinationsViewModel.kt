package com.example.discover_destination.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.uscases.DestinationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DestinationsViewModelInterface{
    fun getOne(city: String): Destination
    fun add(destination: Destination)
    fun delete(city: String)
    fun modify(destination: Destination)
    fun filter(country: String, user: String): List<Destination>
}

@HiltViewModel
class DestinationsViewModel @Inject constructor(
    private val useCase: DestinationsUseCase
) : ViewModel(), DestinationsViewModelInterface {
    private val _listOfDestinations: MutableState<List<Destination>> = mutableStateOf(emptyList())
    val listOfDestinations: State<List<Destination>> = _listOfDestinations

    init {
        viewModelScope.launch {
            val destinationList = useCase()
            _listOfDestinations.value = destinationList
        }
    }

    override fun getOne(city: String): Destination{
        return useCase.invokeGetOne(city)
    }

    override fun filter(country: String, user: String): List<Destination>{
        return useCase.invokeFilter(country, user)
    }

    override fun add(destination: Destination){
        useCase.invokeAdd(destination)
    }

    override fun delete(city: String){
        useCase.invokeDelete(city)
    }

    override fun modify(destination: Destination){
        useCase.invokeModify(destination)
    }
}