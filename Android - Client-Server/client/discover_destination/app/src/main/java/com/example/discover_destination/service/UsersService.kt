package com.example.discover_destination.service

import com.example.discover_destination.domain.User
import javax.inject.Inject

class UsersService @Inject constructor() {
    private var users: MutableList<User> = mutableListOf(User("ana", "pass"), User("ion", "pass"))

    fun checkUser(user: User): Boolean {
        return !users.filter{u -> user.username == u.username && user.password == u.password}.isEmpty()
    }
}