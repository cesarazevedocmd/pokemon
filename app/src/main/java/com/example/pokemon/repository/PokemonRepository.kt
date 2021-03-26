package com.example.pokemon.repository

import androidx.lifecycle.LiveData
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.PokemonClientService
import com.example.pokemon.ui.viewmodel.provider.Resource

open class PokemonRepository(private val clientService: PokemonClientService) {

    fun items(): LiveData<Resource<List<Pokemon>>> = clientService.items()

    suspend fun listItems() = clientService.listItems()

}