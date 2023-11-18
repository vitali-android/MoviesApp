package com.shukevich.moviesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shukevich.moviesapp.databinding.FragmentMoviesListBinding


class FragmentMoviesList : Fragment() {
    private var _binding:  FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var movieListListener: MovieListListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        binding.cardMovie.setOnClickListener {
            movieListListener?.toMovieDetail()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieListListener){
            movieListListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        movieListListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MovieListListener{
        fun toMovieDetail()
    }
}