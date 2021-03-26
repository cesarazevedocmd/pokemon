package com.example.pokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.ui.viewmodel.provider.CoroutineContextProvider
import com.example.pokemon.ui.viewmodel.provider.Resource

class PokemonViewModel(
    private val repository: PokemonRepository,
    contextProvider: CoroutineContextProvider
) :
    SuperViewModel(contextProvider) {

    fun items(): LiveData<Resource<List<Pokemon>>> = repository.items()

    fun listItems() {
        launch {
            repository.listItems()
        }
    }

}