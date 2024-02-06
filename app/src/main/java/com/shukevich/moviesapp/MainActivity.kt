package com.shukevich.moviesapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shukevich.moviesapp.data.JsonMovieRepository
import com.shukevich.moviesapp.data.MovieRepository
import com.shukevich.moviesapp.databinding.ActivityMainBinding
import com.shukevich.moviesapp.di.MovieRepositoryProvider
import com.shukevich.moviesapp.features.moviedetails.MovieDetailsFragment
import com.shukevich.moviesapp.features.movies.MoviesListFragment
import com.shukevich.moviesapp.model.Movie

class MainActivity : AppCompatActivity(),
    MoviesListFragment.MoviesListItemClickListener,
    MovieDetailsFragment.MovieDetailsBackClickListener,
    MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MoviesListFragment.create(),
                MoviesListFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MoviesListFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeToMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                MovieDetailsFragment.create(movie.id),
                MovieDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}

