package com.example.pokemon.repository

import androidx.lifecycle.LiveData
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.PokemonClientService

open class PokemonRepository(private val clientService: PokemonClientService) {

    fun items(): LiveData<List<Pokemon>> = clientService.items()

    suspend fun listItems() = clientService.listItems()

}