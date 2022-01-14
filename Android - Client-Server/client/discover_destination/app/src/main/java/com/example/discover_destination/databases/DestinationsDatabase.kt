package com.example.discover_destination.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.domain.User

@Database(entities = [Destination::class, User::class], version = 1)
abstract class DestinationsDatabase : RoomDatabase() {

    abstract fun destinationsDao(): DestinationsDao
    abstract fun usersDao(): UsersDao

    companion object{
        private var dbInstance: DestinationsDatabase? = null

        fun getBD(context: Context): DestinationsDatabase{
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context.applicationContext,
                    DestinationsDatabase::class.java, "destinations.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}