package com.shukevich.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shukevich.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMainActivity.setOnClickListener{
            movieToMovieDetails()
        }
    }

    private fun movieToMovieDetails() {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        startActivity(intent)
    }
}