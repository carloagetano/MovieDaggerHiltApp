package com.example.moviedaggerhiltapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.moviedaggerhiltapp.data.database.entity.MovieEntity
import com.example.moviedaggerhiltapp.data.database.entity.PostersEntity
import com.example.moviedaggerhiltapp.data.database.relations.MovieWithPosters

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovie(movieEntity: MovieEntity): Long

    @Upsert
    suspend fun upsertPosters(postersEntity: List<PostersEntity>)

    @Transaction
    suspend fun upsertMovieWithPosters(
        movieEntity: MovieEntity,
        postersList: List<String>
    ) {
        val insertedMovieId = upsertMovie(movieEntity = movieEntity)

        val posters = postersList.map { imgUrl ->
            PostersEntity(imgUrl = imgUrl, movieId = insertedMovieId)
        }

        upsertPosters(posters)
    }

    @Transaction
    @Query("SELECT * FROM movie_table ORDER BY movieId ASC ")
    fun getAllMoviesWithPosters(): LiveData<List<MovieWithPosters>>

}