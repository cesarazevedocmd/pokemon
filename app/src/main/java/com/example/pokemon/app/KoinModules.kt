package com.example.pokemon.app

import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.repository.remote.PokemonClientService
import com.example.pokemon.repository.remote.PokemonService
import com.example.pokemon.repository.retrofit.PokemonAPI
import com.example.pokemon.ui.viewmodel.PokemonViewModel
import com.example.pokemon.ui.viewmodel.provider.CoroutineContextProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val clientServices = module {
    single { PokemonClientService(get()) }
}

val services = module {
    single { PokemonAPI.create(PokemonService::class.java) }
}

val repository = module {
    single { PokemonRepository(get()) }
}

val viewModel = module {
    viewModel { PokemonViewModel(get(), CoroutineContextProvider()) }
}

val modules = listOf(clientServices, services, repository, viewModel)