package com.example.discover_destination.domain

data class Destination(val city: String, val country: String, val tourism: String, val description: String,
                  val image: String, val touristAttractions: List<String>, val user: String) {

}