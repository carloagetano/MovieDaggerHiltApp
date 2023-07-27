package com.example.moviedaggerhiltapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedaggerhiltapp.data.database.dao.MovieDao
import com.example.moviedaggerhiltapp.data.database.entity.MovieEntity
import com.example.moviedaggerhiltapp.data.database.entity.PostersEntity

@Database(
    entities = [
        MovieEntity::class,
        PostersEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}