package com.example.discover_destination.uscases

import com.example.discover_destination.domain.User

interface UsersUseCasesInterface{
    suspend operator fun invoke()
    fun invokeCheckUser(user: User): Boolean
}
