package com.example.moviedaggerhiltapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posters_table")
data class PostersEntity(
    @PrimaryKey(autoGenerate = true)
    val postersId: Long? = 0L,
    val imgUrl: String?,
    val movieId: Long
)
