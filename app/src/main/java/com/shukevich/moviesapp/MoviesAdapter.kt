package com.shukevich.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shukevich.moviesapp.data.models.Movie
import com.shukevich.moviesapp.databinding.ViewHolderMovieBinding

class MoviesAdapter(
    private val movieItemListener: MovieItemListener
): RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            movieItemListener.onClick()
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }

}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderMovieBinding.bind(itemView)

    fun onBind(movie: Movie) = with(binding){
        ageRating.text = movie.ageRating
        imgPoster.setImageResource(movie.poster)
        movieTitle.text = movie.title
        movieLike.setImageResource(if (movie.like) R.drawable.ic_purple_like else R.drawable.ic_like)
        ratingBarCard.rating = movie.rating.toFloat()
        reviewsCount.text = movie.review.toString()
        genre.text = movie.genre
        runTime.text = movie.runTime.toString()
    }

}

interface MovieItemListener{
    fun onClick()
}


