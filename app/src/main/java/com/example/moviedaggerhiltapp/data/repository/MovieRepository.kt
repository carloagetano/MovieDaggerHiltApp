package com.example.moviedaggerhiltapp.data.repository

import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.data.utils.Resource

interface MovieRepository {

    suspend fun getMovies(): Resource<ArrayList<MovieResponse>>
}