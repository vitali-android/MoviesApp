package com.shukevich.moviesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.shukevich.moviesapp.databinding.FragmentMoviesListBinding
import com.shukevich.moviesapp.domain.MoviesDataSource


class FragmentMoviesList : Fragment() {
    private var _binding:  FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private var listener : MoviesListListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcMoviesList.adapter = MoviesAdapter(movieItemListener)
            rcMoviesList.layoutManager = GridLayoutManager(context,2)
            updateData()
        }

    }

    private fun updateData()= with(binding){
        (rcMoviesList.adapter as MoviesAdapter).apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

    private val movieItemListener = object : MovieItemListener{
        override fun onClick() {
            listener?.toMovieDetail()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? MoviesListListener

    }

    override fun onDetach() {
        super.onDetach()
        listener = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MoviesListListener {
        fun toMovieDetail()
    }

}