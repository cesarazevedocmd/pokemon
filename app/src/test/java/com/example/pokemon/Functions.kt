package com.example.pokemon

import com.example.pokemon.repository.model.Pokemon

fun createPokemonList(requestCode: Int, quantity: Int): List<Pokemon> {
    val pokemonList = arrayListOf<Pokemon>()
    repeat(quantity) {
        pokemonList.add(Pokemon("POKEMON ${it + 1} ; REQUEST $requestCode"))
    }
    return pokemonList
}