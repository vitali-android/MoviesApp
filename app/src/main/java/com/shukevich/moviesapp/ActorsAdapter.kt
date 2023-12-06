package com.shukevich.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shukevich.moviesapp.data.models.Actor
import com.shukevich.moviesapp.databinding.ViewHolderActorBinding

class ActorsAdapter(): RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
       return ActorViewHolder(
           LayoutInflater.from(parent.context)
               .inflate(R.layout.view_holder_actor, parent, false)
       )
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    fun bindActors(newActors: List<Actor>){
        actors = newActors
    }
}

class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderActorBinding.bind(itemView)

    fun onBind(actor: Actor)= with(binding){
        photoActor.setImageResource(actor.photo)
        nameActor.text = actor.name
    }

}
