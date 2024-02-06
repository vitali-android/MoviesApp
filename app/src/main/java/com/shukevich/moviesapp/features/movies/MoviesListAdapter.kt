package com.shukevich.moviesapp.features.movies


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shukevich.moviesapp.R
import com.shukevich.moviesapp.databinding.ViewHolderMovieBinding
import com.shukevich.moviesapp.model.Movie

class MoviesListAdapter(private val onClickCard: (item: Movie) -> Unit) :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewHolderMovieBinding.bind(itemView)

        private val starsImages: List<ImageView> = listOf(
            binding.star1Image,
            binding.star2Image,
            binding.star3Image,
            binding.star4Image,
            binding.star5Image
        )


        fun bind(item: Movie, onClickCard: (item: Movie) -> Unit) = with(binding) {
            val context = itemView.context
            movieImage.load(item.imageUrl)
            pgText.text = context.getString(R.string.movies_list_allowed_age_template, item.pgAge)
            filmGenreText .text = item.genres.joinToString { it.name }
            movieReviewsCountText .text = context.getString(R.string.movies_list_reviews_template, item.reviewCount)
            filmNameText.text = item.title
            filmTimeText .text = context.getString(R.string.movies_list_film_time, item.runningTime)

            val likeColor = if (item.isLiked) {
                R.color.pink_light
            } else {
                R.color.color_white
            }
            ImageViewCompat.setImageTintList(
                movieLikeImage, ColorStateList.valueOf(
                    ContextCompat.getColor(movieLikeImage.context, likeColor)
                )
            )

            //set stars tint
            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (item.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}



