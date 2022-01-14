package com.example.discover_destination.uscases

import com.example.discover_destination.databases.UsersDao
import com.example.discover_destination.domain.User
import javax.inject.Inject

class UsersUseCases @Inject constructor(
    val repo: UsersDao
) : UsersUseCasesInterface{

    override suspend fun invoke() {

    }

    override fun invokeCheckUser(user: User): Boolean {
        return repo.checkUser(user.username, user.password)
    }
}