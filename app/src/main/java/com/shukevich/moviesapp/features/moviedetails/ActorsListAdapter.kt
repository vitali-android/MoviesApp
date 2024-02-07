package com.shukevich.moviesapp.features.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import  com.shukevich.moviesapp.R
import com.shukevich.moviesapp.databinding.ViewHolderActorBinding
import  com.shukevich.moviesapp.model.Actor

class ActorsListAdapter : ListAdapter<Actor, ActorsListAdapter.ViewHolder>(DiffCallback()) {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_actor_placeholder)
        .fallback(R.drawable.ic_actor_placeholder)
        .centerCrop()
//        .circleCrop()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(imageOption,item)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewHolderActorBinding.bind(itemView)

        fun bind(options: RequestOptions,item: Actor)= with(binding) {
            val context = itemView.context
            Glide.with(context)
                .load(item.imageUrl)
                .apply(options)
                .into(actorImage)

            actorName.text = item.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem == newItem
        }
    }
}

