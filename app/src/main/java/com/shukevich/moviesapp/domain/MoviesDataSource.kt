package com.shukevich.moviesapp.domain

import com.shukevich.moviesapp.R
import com.shukevich.moviesapp.data.models.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "13+",
                R.drawable.ic_img_small_poster,
                "Avengers:End Game",
                false,
                4,
                125,
                "Action, Adventure, Fantasy",
                137
            ),
            Movie(
                "16+",
                R.drawable.ic_img_small_poster2,
                "Tenet",
                true,
                5,
                98,
                "Action, Sci-Fi, Thriller",
                97
            ),
            Movie(
                "13+",
                R.drawable.ic_img_small_poster3,
                "Black Widow",
                false,
                4,
                38,
                "Action, Adventure, Sci-Fi",
                102
            ),
            Movie(
                "13+",
                R.drawable.ic_img_small_poster4,
                "Wonder Woman 1984",
                false,
                5,
                74,
                "Action, Adventure, Fantasy",
                120
            )
        )
    }
}