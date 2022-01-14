package com.example.discover_destination.databases

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UsersDao{

    @Query("SELECT EXISTS(SELECT * FROM users WHERE username LIKE :username AND password LIKE :password)")
    fun checkUser(username: String, password: String): Boolean
}