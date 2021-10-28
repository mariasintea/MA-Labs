package com.example.discover_destination.uscases

import com.example.discover_destination.domain.Destination
import com.example.discover_destination.repository.DestinationsRepositoryInterface
import javax.inject.Inject

interface DestinationsUseCaseInterface {
    suspend operator fun invoke(): List<Destination>
    fun invokeFilter(country: String, user: String): List<Destination>
    fun invokeGetOne(city: String): Destination
    fun invokeAdd(destination: Destination)
    fun invokeDelete(city: String)
    fun invokeModify(destination: Destination)
}

class DestinationsUseCase @Inject constructor(
    val repo: DestinationsRepositoryInterface
) : DestinationsUseCaseInterface {
    override suspend fun invoke(): List<Destination> {
        return repo.getAll()
    }

    override fun invokeFilter(country: String, user: String): List<Destination> {
        return repo.getByCountry(country, user)
    }

    override fun invokeGetOne(city: String): Destination {
        return repo.getOne(city)
    }

    override fun invokeAdd(destination: Destination) {
        return repo.add(destination)
    }

    override fun invokeDelete(city: String) {
        return repo.delete(city)
    }

    override fun invokeModify(destination: Destination) {
        return repo.modify(destination)
    }
}