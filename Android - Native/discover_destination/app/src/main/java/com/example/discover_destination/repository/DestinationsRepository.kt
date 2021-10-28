package com.example.discover_destination.repository

import com.example.discover_destination.DestinationsService
import com.example.discover_destination.domain.Destination
import javax.inject.Inject

interface DestinationsRepositoryInterface {
    fun add(destination: Destination)
    fun delete(city: String)
    fun modify(destination: Destination)
    suspend fun getAll(): List<Destination>
    fun getByCountry(country: String, user: String): List<Destination>
    fun getOne(city: String): Destination
}

class DestinationsRepository @Inject constructor(
    val service: DestinationsService
) : DestinationsRepositoryInterface {
    override fun add(destination: Destination){
        service.addDestination(destination)
    }

    override fun delete(city: String){
        service.deleteDestination(city)
    }

    override fun modify(destination: Destination) {
        service.modifyDestination(destination)
    }

    override suspend fun getAll(): List<Destination> {
        return service.getAllDestinations()
    }

    override fun getByCountry(country: String, user: String): List<Destination> {
        return service.getDestinationsByCountry(country, user)
    }

    override fun getOne(city: String): Destination {
        return service.getDestinationByCity(city)
    }
}
