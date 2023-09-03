package com.example.moviedaggerhiltapp.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "posters_table",
    foreignKeys = [ForeignKey(
        MovieEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class PostersEntity(
    @PrimaryKey(autoGenerate = true)
    val postersId: Long? = 0L,
    val imgUrl: String?,
    val movieId: Long
)
