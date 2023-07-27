package com.example.moviedaggerhiltapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.moviedaggerhiltapp.data.database.entity.MovieEntity
import com.example.moviedaggerhiltapp.data.database.entity.PostersEntity

data class MovieWithPosters(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val moviePosters: List<PostersEntity>
)
