package com.example.pokemon.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.repository.model.Pokemon

open class PokemonClientService(private val service: PokemonService) {

    private val pokemonList = MutableLiveData<List<Pokemon>>()
    private val items = mutableListOf<Pokemon>()

    fun items(): LiveData<List<Pokemon>> = pokemonList

    suspend fun listItems() {
        val offset = pokemonList.value?.size ?: 0
        items.addAll(service.listAll(offset))
        pokemonList.value = items
    }
}