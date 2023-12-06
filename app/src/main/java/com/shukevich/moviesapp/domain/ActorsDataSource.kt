package com.shukevich.moviesapp.domain

import com.shukevich.moviesapp.R
import com.shukevich.moviesapp.data.models.Actor
import com.shukevich.moviesapp.data.models.Movie

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return listOf(
            Actor(
                R.drawable.ic_actor1,
                "Robert Downey Jr."
                ),
            Actor(
                R.drawable.ic_actor2,
                "Mark Ruffalo"
            ),
            Actor(
                R.drawable.ic_actor3,
                "Chris Hemsworth"
            ),
            Actor(
                R.drawable.ic_actor4,
                "Cris Evans"
            )
        )
    }
}