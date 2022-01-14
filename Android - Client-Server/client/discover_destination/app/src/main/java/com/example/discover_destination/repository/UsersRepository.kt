package com.example.discover_destination.repository

import com.example.discover_destination.databases.UsersDao
import com.example.discover_destination.domain.User
import com.example.discover_destination.service.UsersService
import javax.inject.Inject

class UsersRepository @Inject constructor(
    val service: UsersDao
) {
    fun checkUser(user: User): Boolean {
        return service.checkUser(user.username, user.password)
    }
}