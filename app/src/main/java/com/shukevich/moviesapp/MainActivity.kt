package com.shukevich.moviesapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shukevich.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentMoviesList.MoviesListListener, FragmentMoviesDetails.MovieDetailListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container,FragmentMoviesList())
            .commit()


    }

    override fun toMovieDetail() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container,FragmentMoviesDetails())
            .commit()
    }

    override fun toMoviesList() {
        supportFragmentManager.popBackStack()
    }
}

