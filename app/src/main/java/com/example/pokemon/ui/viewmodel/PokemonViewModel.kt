package com.example.pokemon.ui.viewmodel

import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.ui.viewmodel.provider.CoroutineContextProvider

class PokemonViewModel(
    private val repository: PokemonRepository,
    contextProvider: CoroutineContextProvider
) :
    SuperViewModel(contextProvider) {


}