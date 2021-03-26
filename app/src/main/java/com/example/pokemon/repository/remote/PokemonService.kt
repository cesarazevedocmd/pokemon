package com.example.pokemon.repository.remote

import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.retrofit.PokemonAPI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("{api_version}/pokemon?limit=100")
    suspend fun listItems(
        @Query(value = "offset") offset: Int,
        @Path(value = "api_version") api: String = PokemonAPI.VERSION
    ): List<Pokemon>

}