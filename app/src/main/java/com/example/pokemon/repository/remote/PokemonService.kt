package com.example.pokemon.repository.remote

import com.example.pokemon.repository.remote.response.ListResponse
import com.example.pokemon.repository.remote.response.PokemonResponse
import com.example.pokemon.repository.retrofit.PokemonAPI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("{api_version}/pokemon?limit=40")
    suspend fun listItems(
        @Path(value = "api_version") api: String = PokemonAPI.VERSION,
        @Query(value = "offset") offset: Int
    ): ListResponse

    @GET("{api_version}/pokemon/{name}")
    suspend fun findPokemon(
        @Path("name") pokemonName: String,
        @Path(value = "api_version") api: String = PokemonAPI.VERSION
    ): PokemonResponse
}