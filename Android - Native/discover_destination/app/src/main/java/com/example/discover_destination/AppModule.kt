package com.example.discover_destination

import com.example.discover_destination.repository.DestinationsRepository
import com.example.discover_destination.repository.DestinationsRepositoryInterface
import com.example.discover_destination.repository.UsersRepository
import com.example.discover_destination.repository.UsersRepositoryInterface
import com.example.discover_destination.uscases.DestinationsUseCase
import com.example.discover_destination.uscases.DestinationsUseCaseInterface
import com.example.discover_destination.uscases.UsersUseCases
import com.example.discover_destination.uscases.UsersUseCasesInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        @Binds
        @Singleton
        fun provideDestinationsRepository(repo: DestinationsRepository): DestinationsRepositoryInterface

        @Binds
        @Singleton
        fun provideDestinationsUseCase(uc: DestinationsUseCase): DestinationsUseCaseInterface

        @Binds
        @Singleton
        fun provideUsersRepository(repo: UsersRepository): UsersRepositoryInterface

        @Binds
        @Singleton
        fun provideUsersUseCase(uc: UsersUseCases): UsersUseCasesInterface

    }
}