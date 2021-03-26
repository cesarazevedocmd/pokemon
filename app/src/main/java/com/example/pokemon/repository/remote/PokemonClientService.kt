package com.example.pokemon.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.repository.model.Pokemon

open class PokemonClientService(private val service: PokemonService) {

    private val items = mutableListOf<Pokemon>()
    private val pokemonList = MutableLiveData<List<Pokemon>>().apply { value = items }

    fun items(): LiveData<List<Pokemon>> = pokemonList

    suspend fun listItems() {
        val offset = getOffset()
        items.addAll(service.listItems(offset))
        pokemonList.postValue(items)
    }

    private fun getOffset(): Int = pokemonList.value?.size ?: 0
}