package com.example.moviedaggerhiltapp.di

import com.example.moviedaggerhiltapp.data.database.dao.MovieDao
import com.example.moviedaggerhiltapp.data.repository.MovieRepository
import com.example.moviedaggerhiltapp.data.repository.MovieRepositoryImpl
import com.example.moviedaggerhiltapp.data.service.MovieAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieAPIService: MovieAPIService, movieDao: MovieDao): MovieRepository =
        MovieRepositoryImpl(movieAPIService, movieDao)
}