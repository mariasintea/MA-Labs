package com.example.discover_destination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discover_destination.domain.User
import com.example.discover_destination.uscases.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val useCase: UsersUseCases
) : ViewModel(){

    init {
        viewModelScope.launch {
            useCase()
        }
    }

    fun checkUser(user: User): Boolean {
        return useCase.invokeCheckUser(user)
    }
}