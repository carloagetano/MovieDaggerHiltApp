package com.example.moviedaggerhiltapp.presentation.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedaggerhiltapp.data.database.relations.MovieWithPosters
import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.data.repository.MovieRepository
import com.example.moviedaggerhiltapp.data.utils.DispatcherProvider
import com.example.moviedaggerhiltapp.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<ArrayList<MovieResponse>>>()

    val movies: LiveData<Resource<ArrayList<MovieResponse>>>
        get() = _movies

    private val _selectedMovie = MutableLiveData<MovieResponse>()

    val selectedMovie: LiveData<MovieResponse>
        get() = _selectedMovie

    init {
        getMovies()

        getAllMoviesFromDb()
    }

    private fun getMovies() = viewModelScope.launch(dispatcher.io) {
        _movies.postValue(Resource.Loading())

        when (val movieResponse = movieRepository.getMovies()) {
            is Resource.Success -> {
                _movies.postValue(movieResponse)
            }

            is Resource.Error -> {
                _movies.postValue(movieResponse)
            }

            else -> Unit
        }
    }

    fun setSelectedMovie(movie: MovieResponse) {
        _selectedMovie.postValue(movie)
    }

    fun getAllMoviesFromDb(): Flow<List<MovieWithPosters>> =
        movieRepository.getAllMoviesWithPosters()
}
