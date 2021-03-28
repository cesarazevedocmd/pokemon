package com.example.pokemon

import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.response.*

fun createPokemonList(quantity: Int): List<Pokemon> {
    val pokemonList = arrayListOf<Pokemon>()
    repeat(quantity) {
        pokemonList.add(
            Pokemon(
                name = "POKEMON ${it + 1}",
                frontPhotoUrl = "",
                backPhotoUrl = "",
                weight = 0,
                height = 0,
                stats = listOf("A", "B"),
                abilities = listOf("C", "D"),
                types = listOf("E", "F")
            )
        )
    }
    return pokemonList
}

fun createPokemonResponse(): PokemonResponse {
    return PokemonResponse(
        name = "POKEMON",
        weight = 0,
        height = 0,
        stats = listOf(Stat(NameUrl(name = "A", url = "")), Stat(NameUrl(name = "B", url = ""))),
        abilities = listOf(Ability(NameUrl(name = "C", url = "")), Ability(NameUrl(name = "D", url = ""))),
        types = listOf(Type(NameUrl(name = "E", url = "")), Type(NameUrl(name = "F", url = ""))),
        sprites = null
    )
}

fun createListResponse(quantity: Int): ListResponse {
    val itemsListResponse = arrayListOf<ItemListResponse>()
    repeat(quantity) {
        itemsListResponse.add(ItemListResponse("POKEMON ${it + 1}", ""))
    }
    return ListResponse(0, "", "", itemsListResponse)
}