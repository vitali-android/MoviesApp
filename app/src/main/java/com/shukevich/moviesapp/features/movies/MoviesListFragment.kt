package com.shukevich.moviesapp.features.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shukevich.moviesapp.R
import com.shukevich.moviesapp.databinding.FragmentMoviesListBinding
import com.shukevich.moviesapp.di.MovieRepositoryProvider
import com.shukevich.moviesapp.model.Movie
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment() {

    private var listener: MoviesListItemClickListener? = null

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMovies.apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MoviesListAdapter { movieData ->
                listener?.onMovieSelected(movieData)
            }

            this.adapter = adapter

            loadDataToAdapter(adapter)  }

    }

    private fun loadDataToAdapter(adapter: MoviesListAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
            val moviesData = repository.loadMovies()

            adapter.submitList(moviesData)
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }

    companion object {
        fun create() = MoviesListFragment()
    }
}