package com.example.discover_destination.databases

import androidx.room.*
import com.example.discover_destination.domain.Destination
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationsDao {
    @Insert
    fun add(destination: Destination)

    @Delete
    fun delete(destination: Destination)

    @Update
    fun modify(destination: Destination)

    @Query("DELETE FROM destinations")
    fun deleteAll()

    @Query("DELETE FROM destinations WHERE is_uploaded = 0")
    fun deleteAllLocal()

    @Insert
    fun addAll(destinations: List<Destination>)

    @Query("SELECT * FROM destinations")
    fun getAll(): Flow<List<Destination>>

    @Query("SELECT * FROM destinations WHERE is_uploaded = 0")
    fun getAllLocal(): Flow<List<Destination>>

    @Query("SELECT * FROM destinations WHERE country LIKE :country AND user LIKE :user")
    fun getByCountry(country: String, user: String): Flow<List<Destination>>

    @Query("SELECT * FROM destinations WHERE city LIKE :city")
    fun getOne(city: String): Destination
}