package com.example.discover_destination.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.uscases.DestinationsUseCases
import com.example.discover_destination.utils.networkConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DestinationsViewModel @Inject constructor(
    private val useCases: DestinationsUseCases
) : ViewModel() {
    private val _listOfDestinations: MutableLiveData<List<Destination>> = MutableLiveData<List<Destination>>(listOf())
    val listOfDestinations: LiveData<List<Destination>> = _listOfDestinations
    val isRetrievedSuccessfully = MutableLiveData<Boolean>()


    fun getAllLocal(){
        viewModelScope.launch {
            try{
                useCases.invokeGetAllLocalDestinations().collect{
                    _listOfDestinations.postValue(it)
                    isRetrievedSuccessfully.postValue(true)
                }
            } catch (exception : Exception){
                isRetrievedSuccessfully.postValue(false)
                Log.d("error", "", exception)
            }
        }
    }

    fun loadDestinations(context: Context, showError: (String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        viewModelScope.launch {
            val connectivity = networkConnectivity(context, syncDestinations)
            if (!connectivity) {
                showError("No internet connection!")
            } else {
                useCases.invokeLoadAllDestinations(showError, progressIndicatorVisibility)
            }
        }
    }

    val syncDestinations: () -> Unit = {
        viewModelScope.launch {
            useCases.invokeSyncDestinations()
        }
    }

    fun getOne(city: String): Destination {
        var destination: Destination = Destination(0, "", "", "", "", "", "", "")
        viewModelScope.launch {
            destination = useCases.invokeGetOne(city)
        }
        return destination
    }

    fun filter(country: String, user: String): List<Destination> {
        var destinations: List<Destination> = mutableListOf()
        viewModelScope.launch {
            useCases.invokeFilter(country, user).collect{
                destinations = it
            }
        }
        return destinations
    }

    fun add(context: Context, destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        viewModelScope.launch(Dispatchers.IO){
            val connectivity = networkConnectivity(context, syncDestinations)
            if (!connectivity) {
                showMessage(true, "No internet connection!")
                destination.isUploaded = false
                useCases.invokeAddLocally(destination)
            } else {
                destination.isUploaded = true
                useCases.invokeAdd(destination, showMessage, progressIndicatorVisibility)
            }
        }
    }

    fun delete(context: Context, destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        viewModelScope.launch(Dispatchers.IO){
            val connectivity = networkConnectivity(context, syncDestinations)
            if (!connectivity) {
                showMessage(false, "No internet connection! Cannot delete destination!")
            }else {
                useCases.invokeDelete(destination, showMessage, progressIndicatorVisibility)
            }
        }
    }

    fun modify(context: Context, destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        viewModelScope.launch(Dispatchers.IO) {
            val connectivity = networkConnectivity(context, syncDestinations)
            if (!connectivity) {
                showMessage(false, "No internet connection! Cannot delete destination!")
            } else {
                useCases.invokeModify(destination, showMessage, progressIndicatorVisibility)
            }
        }
    }
}