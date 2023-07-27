package com.example.moviedaggerhiltapp.data.repository

import androidx.lifecycle.LiveData
import com.example.moviedaggerhiltapp.data.database.dao.MovieDao
import com.example.moviedaggerhiltapp.data.database.entity.MovieEntity
import com.example.moviedaggerhiltapp.data.database.entity.PostersEntity
import com.example.moviedaggerhiltapp.data.database.relations.MovieWithPosters
import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.data.service.MovieAPIService
import com.example.moviedaggerhiltapp.data.utils.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAPIService: MovieAPIService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(): Resource<ArrayList<MovieResponse>> {
        return try {
            val response = movieAPIService.getMovies()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result, response.message())
            } else {
                Resource.Error(null, response.message())
            }
        } catch (error: Exception) {
            Resource.Error(null, error.message ?: "An error occurred")
        }
    }

    override suspend fun upsertMovie(movieEntity: MovieEntity): Long {
        return movieDao.upsertMovie(movieEntity = movieEntity)
    }

    override suspend fun upsertPosters(postersEntity: List<PostersEntity>) {
        movieDao.upsertPosters(postersEntity = postersEntity)
    }

    override suspend fun upsertMovieWithPosters(
        movieEntity: MovieEntity,
        postersList: List<String>
    ) {
        movieDao.upsertMovieWithPosters(movieEntity = movieEntity, postersList = postersList)
    }

    override fun getAllMoviesWithPosters(): LiveData<List<MovieWithPosters>> {
        return movieDao.getAllMoviesWithPosters()
    }
}