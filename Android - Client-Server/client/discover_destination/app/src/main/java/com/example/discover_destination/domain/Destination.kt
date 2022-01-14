package com.example.discover_destination.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class Destination(
    @PrimaryKey(autoGenerate = false) var id: Int? = null,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "tourism") val tourism: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "tourist_attractions") val touristAttractions: String,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "is_uploaded") var isUploaded : Boolean? = null
) {}