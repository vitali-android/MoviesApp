package com.shukevich.moviesapp.di

import com.shukevich.moviesapp.data.MovieRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}