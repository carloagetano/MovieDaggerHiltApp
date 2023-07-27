package com.example.moviedaggerhiltapp.di

import android.app.Application
import androidx.room.Room
import com.example.moviedaggerhiltapp.data.database.MovieDatabase
import com.example.moviedaggerhiltapp.data.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase = Room.databaseBuilder(
        application,
        MovieDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()
}