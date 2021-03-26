package com.example.pokemon.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.response.ItemListResponse
import com.example.pokemon.ui.viewmodel.provider.Resource
import com.example.pokemon.ui.viewmodel.provider.Status

open class PokemonClientService(private val service: PokemonService) {

    private val items = mutableListOf<Pokemon>()
    private val pokemonList = MutableLiveData<Resource<List<Pokemon>>>().apply { this.value = Resource.success(listOf()) }

    fun items(): LiveData<Resource<List<Pokemon>>> = pokemonList

    suspend fun listItems() {
        if (pokemonList.value?.status != Status.LOADING) {
            pokemonList.postValue(Resource.loading())
            try {
                items.addAll(loadItems())
                pokemonList.postValue(Resource.success(items))
            } catch (exception: RuntimeException) {
                pokemonList.postValue(Resource.error(exception.message ?: "Error, something went wrong"))
            }
        }
    }

    private suspend fun loadItems(): List<Pokemon> {
        val offset = getOffset()
        val list = service.listItems(offset = offset)
        val result = mutableListOf<Pokemon>()
        list.results.forEach {
            val pokemon = loadPokemonDetails(it)
            result.add(pokemon)
        }
        return result
    }

    private suspend fun loadPokemonDetails(it: ItemListResponse): Pokemon {
        val pokemonFound = service.findPokemon(it.name)
        return Pokemon(
            name = it.name,
            frontPhotoUrl = pokemonFound.sprites?.frontPhoto ?: "",
            backPhotoUrl = pokemonFound.sprites?.backPhoto ?: "",
            weight = pokemonFound.weight,
            height = pokemonFound.height,
            stats = pokemonFound.stats?.map { it.stat.name } ?: listOf(),
            abilities = pokemonFound.abilities?.map { it.ability.name } ?: listOf(),
            types = pokemonFound.types?.map { it.type.name } ?: listOf()
        )
    }

    private fun getOffset(): Int = pokemonList.value?.data?.size ?: 0
}