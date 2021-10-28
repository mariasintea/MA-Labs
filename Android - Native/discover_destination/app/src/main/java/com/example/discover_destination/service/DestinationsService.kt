package com.example.discover_destination

import com.example.discover_destination.domain.Destination
import javax.inject.Inject

class DestinationsService @Inject constructor(){
    private var destinations: MutableList<Destination> =
        mutableListOf(Destination("Rome", "Italy", "cultural", "Nice place", "https://thenomadvisor.com/wp-content/uploads/2020/02/romewhertostay-780x520.jpg", listOf("Colosseum", "Trevi Fountain"), "ana"),
            Destination("London", "UK", "cultural", "Nice place", "https://www.27vakantiedagen.nl/wp-content/uploads/2019/05/engeland-londen-palace-of-westminster.jpg", listOf("Big Ben", "Buckingham Palace"), "ana"),
             Destination("Paris", "France", "cultural", "Nice place", "https://images.adsttc.com/media/images/5d44/14fa/284d/d1fd/3a00/003d/large_jpg/eiffel-tower-in-paris-151-medium.jpg?1564742900", listOf("Eiffel Tower", "Versailles Palace"), "ion"))

    fun addDestination(destination: Destination){
        destinations += destination
    }

    fun deleteDestination(city: String){
        val destination = getDestinationByCity(city)
        destinations -= destination
    }

    fun modifyDestination(destination: Destination){
        val index: Int = destinations.indexOf(getDestinationByCity(destination.city))
        destinations[index] = destination
    }

    fun getAllDestinations(): List<Destination>{
        return destinations
    }

    fun getDestinationByCity(city: String): Destination{
        if (!destinations.filter{ destination -> destination.city.equals(city) }.isEmpty())
            return destinations.filter{ destination -> destination.city.equals(city) }.first()
        else
            return Destination("", "", "", "", "", emptyList(), "")
    }

    fun getDestinationsByCountry(country: String, user: String): List<Destination>{
        if (country.equals(""))
            return destinations
        else
            return destinations.filter{ destination -> destination.country.equals(country) &&
                    destination.user.equals(user) }
    }
}