package com.example.discover_destination.service

import com.example.discover_destination.domain.Destination
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

interface DestinationsService{

    @POST("/destination")
    fun addDestination(@Body d: Destination): Observable<Destination>

    @POST("/destinations")
    fun addDestinations(@Body destinations: List<Destination>): Observable<List<Destination>>

    @DELETE("/destinations/{id}")
    suspend fun deleteDestination(@Path("id") id: Int): Response<Unit>

    @PUT("/destination")
    fun modifyDestination(@Body d:Destination): Observable<Destination>

    @GET("/destinations")
    fun getAllDestinations(): Observable<List<Destination>>

    companion object{
        //const val SERVICE_ENDPOINT = "http://192.168.1.8:3000"
        const val SERVICE_ENDPOINT = "http://10.0.2.2:3000"
    }
}