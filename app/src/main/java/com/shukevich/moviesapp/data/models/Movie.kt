package com.shukevich.moviesapp.data.models

data class Movie(
    val ageRating: String,
    val poster: Int,
    val title: String,
    val like: Boolean,
    val rating: Int,
    val review: Int,
    val genre: String,
    val runTime: Int
)
