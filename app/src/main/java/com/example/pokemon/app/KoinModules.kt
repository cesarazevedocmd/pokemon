package com.example.pokemon.app

import com.example.pokemon.repository.retrofit.PokemonAPI
import com.example.pokemon.repository.services.PokemonClient
import com.example.pokemon.repository.services.PokemonService
import org.koin.dsl.module

val clientServices = module {
    single { PokemonClient(get()) }
}

val services = module {
    single { PokemonAPI.create(PokemonService::class.java) }
}

val modules = listOf(clientServices, services)