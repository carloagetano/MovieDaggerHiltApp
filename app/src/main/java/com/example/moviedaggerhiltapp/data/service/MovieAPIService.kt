package com.example.moviedaggerhiltapp.data.service

import com.example.moviedaggerhiltapp.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPIService {

    @GET("/movies")
    suspend fun getMovies(): Response<ArrayList<MovieResponse>>
}