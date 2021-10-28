package com.example.discover_destination.uscases

import com.example.discover_destination.domain.User
import com.example.discover_destination.repository.UsersRepositoryInterface
import javax.inject.Inject

interface UsersUseCasesInterface{
    suspend operator fun invoke()
    fun invokeCheckUser(user: User): Boolean
}

class UsersUseCases @Inject constructor(
    val repo: UsersRepositoryInterface
) : UsersUseCasesInterface{

    override suspend fun invoke() {

    }

    override fun invokeCheckUser(user: User): Boolean {
        return repo.checkUser(user)
    }
}