package com.example.moviedaggerhiltapp.data.repository

import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.data.service.MovieAPIService
import com.example.moviedaggerhiltapp.data.utils.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAPIService: MovieAPIService
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
}