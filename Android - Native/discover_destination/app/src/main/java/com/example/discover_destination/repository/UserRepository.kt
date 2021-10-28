package com.example.discover_destination.repository

import com.example.discover_destination.domain.User
import com.example.discover_destination.service.UsersService
import javax.inject.Inject

interface UsersRepositoryInterface{
    fun checkUser(user: User): Boolean
}

class UsersRepository @Inject constructor(
    val service: UsersService
) : UsersRepositoryInterface{

    override fun checkUser(user: User): Boolean {
        return service.checkUser(user)
    }
}