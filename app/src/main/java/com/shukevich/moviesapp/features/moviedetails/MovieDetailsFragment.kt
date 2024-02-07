package com.shukevich.moviesapp.features.moviedetails

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shukevich.moviesapp.R
import com.shukevich.moviesapp.databinding.FragmentMovieDetailsBinding
import com.shukevich.moviesapp.di.MovieRepositoryProvider
import com.shukevich.moviesapp.model.Movie
import kotlinx.coroutines.launch


class MovieDetailsFragment : Fragment() {

    private var listener: MovieDetailsBackClickListener? = null
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_movieholder)
        .fallback(R.drawable.ic_movieholder)
        .centerCrop()
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return

        binding.recyclerMovies.apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            this.adapter = ActorsListAdapter()
        }

        binding.backButtonLayout.setOnClickListener {
            listener?.onMovieDeselected()
        }
        lifecycleScope.launch {
            val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
            val movie = repository.loadMovie(movieId)

            if (movie != null) {
                bindUI(view, movie)
            } else {
                showMovieNotFoundError()
            }
        }
    }

    private fun showMovieNotFoundError() {
        Toast.makeText(requireContext(), R.string.error_movie_not_found, Toast.LENGTH_LONG)
            .show()
    }

    private fun bindUI(view: View, movie: Movie) {
        updateMovieDetailsInfo(movie)
        val adapter =
            binding.recyclerMovies.adapter as ActorsListAdapter
        adapter.submitList(movie.actors)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie: Movie) = with(binding) {

//        movieLogoImage.load(movie.detailImageUrl)
        Glide.with(context)
            .load(movie.detailImageUrl)
            .apply(imageOption)
            .into(movieLogoImage)

        movieAgeRestrictionsText.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)
        movieNameText.text = movie.title
        movieTagsText.text = movie.genres.joinToString { it.name }
        movieReviewsCountText.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        movieStorylineText.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            star1Image,
            star2Image,
            star3Image,
            star4Image,
            star5Image
        )
        
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun create(movieId: Int) = MovieDetailsFragment().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }
}