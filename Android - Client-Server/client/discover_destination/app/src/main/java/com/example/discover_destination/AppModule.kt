package com.example.discover_destination

import android.content.Context
import com.example.discover_destination.databases.DestinationsDatabase
import com.example.discover_destination.databases.UsersDao
import com.example.discover_destination.repository.DestinationsRepository
import com.example.discover_destination.repository.DestinationsRepositoryInterface
import com.example.discover_destination.service.DestinationsService
import com.example.discover_destination.uscases.DestinationsUseCases
import com.example.discover_destination.uscases.DestinationsUseCasesInterface
import com.example.discover_destination.uscases.UsersUseCases
import com.example.discover_destination.uscases.UsersUseCasesInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {
        @Binds
        @Singleton
        fun provideDestinationsUseCase(uc: DestinationsUseCases): DestinationsUseCasesInterface

        @Binds
        @Singleton
        fun provideUsersUseCase(uc: UsersUseCases): UsersUseCasesInterface
    }

    @Provides
    @Singleton
    fun provideUsersDao(destinationsDatabase: DestinationsDatabase): UsersDao {
        return destinationsDatabase.usersDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): DestinationsDatabase {
        return DestinationsDatabase.getBD(context)
    }

    @Provides
    @Singleton
    fun provideDestinationsRepository(@ApplicationContext appContext: Context): DestinationsRepositoryInterface{
        val dao = DestinationsDatabase.getBD(appContext).destinationsDao()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DestinationsService.SERVICE_ENDPOINT)
            .build()

        val service = retrofit.create(DestinationsService::class.java)
        return DestinationsRepository(dao, service)
    }
}