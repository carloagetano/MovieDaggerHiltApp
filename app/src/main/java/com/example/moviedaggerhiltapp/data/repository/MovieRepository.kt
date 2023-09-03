package com.example.moviedaggerhiltapp.data.repository

import androidx.lifecycle.LiveData
import com.example.moviedaggerhiltapp.data.database.entity.MovieEntity
import com.example.moviedaggerhiltapp.data.database.entity.PostersEntity
import com.example.moviedaggerhiltapp.data.database.relations.MovieWithPosters
import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Resource<ArrayList<MovieResponse>>

    suspend fun upsertMovie(movieEntity: MovieEntity): Long

    suspend fun upsertPosters(postersEntity: List<PostersEntity>)

    suspend fun upsertMovieWithPosters(
        movieEntity: MovieEntity,
        postersList: List<String>
    )

    fun getAllMoviesWithPosters(): Flow<List<MovieWithPosters>>
}