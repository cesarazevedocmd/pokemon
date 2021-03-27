package com.example.pokemon.repository.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.response.ItemListResponse
import com.example.pokemon.ui.viewmodel.provider.Resource
import com.example.pokemon.ui.viewmodel.provider.Status

open class PokemonClientService(private val service: PokemonService) {

    private val items = mutableListOf<Pokemon>()
    private val pokemonList = MutableLiveData<Resource<List<Pokemon>>>().apply { this.value = Resource.finished(items) }

    fun items(): LiveData<Resource<List<Pokemon>>> = pokemonList

    suspend fun listItems() {
        if (pokemonList.value?.status == Status.FINISHED || items.isEmpty()) {
            try {
                pokemonList.postValue(Resource.loading())
                loadItems()
                pokemonList.postValue(Resource.finished(items))
            } catch (exception: RuntimeException) {
                pokemonList.postValue(Resource.error(exception.localizedMessage ?: "Error, something went wrong"))
            }
        }
    }

    @Synchronized
    private suspend fun loadItems() {
        val offset = getOffset()
        val list = service.listItems(offset = offset)
        list.results.forEach { response ->
            loadPokemonDetails(response)?.also {
                addNewItem(it)
            }
        }
    }

    private fun addNewItem(pokemon: Pokemon) {
        items.add(pokemon)
        pokemonList.postValue(Resource.updateInProgress(items))
    }

    private suspend fun loadPokemonDetails(response: ItemListResponse): Pokemon? {
        return try {
            val pokemonFound = service.findPokemon(response.name)
            Pokemon(
                name = response.name,
                frontPhotoUrl = pokemonFound.sprites?.frontPhoto ?: "",
                backPhotoUrl = pokemonFound.sprites?.backPhoto ?: "",
                weight = pokemonFound.weight,
                height = pokemonFound.height,
                stats = pokemonFound.stats?.map { it.stat.name } ?: listOf(),
                abilities = pokemonFound.abilities?.map { it.ability.name } ?: listOf(),
                types = pokemonFound.types?.map { it.type.name } ?: listOf()
            )
        } catch (exception: RuntimeException) {
            Log.i("PKM_RESULT", "loadPokemonDetails, pokemon ${response.name} -> ${exception.message}")
            null
        }
    }

    private fun getOffset(): Int = items.size
}