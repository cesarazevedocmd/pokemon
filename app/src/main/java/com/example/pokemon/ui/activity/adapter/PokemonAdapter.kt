package com.example.pokemon.ui.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.repository.model.Pokemon

class PokemonAdapter(private val itemClick: (Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val items = mutableListOf<Pokemon>()

    fun setItems(items: List<Pokemon>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, itemClick)
    }

    override fun getItemCount(): Int = items.size

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, itemClick: (Pokemon) -> Unit) {
            itemView.setOnClickListener { itemClick(pokemon) }
            itemView.findViewById<AppCompatTextView>(R.id.pokemon_item_name).text = pokemon.name
            if (pokemon.frontPhotoUrl.run { this.isNotEmpty() && this.isNotBlank() }) {
                Glide
                    .with(itemView.context)
                    .load(pokemon.frontPhotoUrl)
                    .centerCrop()
                    .into(itemView.findViewById<AppCompatImageView>(R.id.pokemon_item_icon))
            }
        }
    }
}