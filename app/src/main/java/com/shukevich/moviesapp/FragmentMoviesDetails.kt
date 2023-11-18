package com.shukevich.moviesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shukevich.moviesapp.databinding.FragmentMoviesDetailsBinding


class FragmentMoviesDetails : Fragment() {

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private var movieDetailListener: MovieDetailListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater,container, false)

        binding.backTextView.setOnClickListener {
            movieDetailListener?.toMoviesList()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieDetailListener){
            movieDetailListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        movieDetailListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

interface MovieDetailListener{
   fun  toMoviesList()
}
}