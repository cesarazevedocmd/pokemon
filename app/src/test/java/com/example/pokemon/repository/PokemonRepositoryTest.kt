package com.example.pokemon.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.repository.remote.PokemonClientService
import com.example.pokemon.repository.remote.PokemonService
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pokemonService: PokemonService

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        val pokemonClientService = PokemonClientService(pokemonService)
        pokemonRepository = PokemonRepository(pokemonClientService)
    }

    @Test
    fun should_Return100Pokemon_WhenCallOnce() {
        val listWith100Pokemon = createPokemonList(0, 100)

        runBlocking {
            `when`(pokemonService.listAll(0)).thenReturn(listWith100Pokemon)

            val items: LiveData<List<Pokemon>> = pokemonRepository.items()
            pokemonRepository.listItems()

            assertThat(items.value, equalTo(listWith100Pokemon))
        }
    }

    @Test
    fun should_Return200Pokemon_WhenCallTwice() {
        val listWith100Pokemon = createPokemonList(0, 100)
        val newListWith100Pokemon = createPokemonList(1, 100)
        val resultList = listWith100Pokemon + newListWith100Pokemon

        runBlocking {
            `when`(pokemonService.listAll(0)).thenReturn(listWith100Pokemon)
            `when`(pokemonService.listAll(100)).thenReturn(newListWith100Pokemon)

            val items: LiveData<List<Pokemon>> = pokemonRepository.items()
            pokemonRepository.listItems()
            pokemonRepository.listItems()

            assertThat(items.value, equalTo(resultList))
        }
    }

    private fun createPokemonList(request: Int, quantity: Int): List<Pokemon> {
        val pokemonList = arrayListOf<Pokemon>()
        repeat(quantity) {
            pokemonList.add(Pokemon("PKM $it ; REQUEST $request"))
        }
        return pokemonList
    }

}