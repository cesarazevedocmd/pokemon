package com.example.pokemon.repository.remote

import com.example.pokemon.repository.retrofit.PokemonAPI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("{api_version}/pokemon?limit=100")
    suspend fun listAll(@Query(value = "offset") offset: Int = 0, @Path(value = "api_version") api: String = PokemonAPI.VERSION)

}