package com.shukevich.moviesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shukevich.moviesapp.databinding.FragmentMoviesDetailsBinding
import com.shukevich.moviesapp.domain.ActorsDataSource


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcActors.adapter = ActorsAdapter()
            rcActors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false )
            updateData()
        }
    }

    private fun updateData()= with(binding) {
        (rcActors.adapter as ActorsAdapter).apply {
            bindActors(ActorsDataSource().getActors())
        }
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