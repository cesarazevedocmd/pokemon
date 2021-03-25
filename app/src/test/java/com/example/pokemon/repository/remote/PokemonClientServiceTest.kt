package com.example.pokemon.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.pokemon.repository.model.Pokemon
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
class PokemonClientServiceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var pokemonClientService: PokemonClientService

    @Mock
    private lateinit var service: PokemonService

    @Before
    fun setUp() {
        pokemonClientService = PokemonClientService(service)
    }

    @Test
    fun should_Return100Pokemon_WhenCallOnce() {
        val listWith100Pokemon = createPokemonList(1, 100)

        runBlocking {
            `when`(service.listAll()).thenReturn(listWith100Pokemon)

            val items: LiveData<List<Pokemon>> = pokemonClientService.items()
            pokemonClientService.listItems()

            assertThat(items.value, equalTo(listWith100Pokemon))
        }
    }

    @Test
    fun should_Return100Pokemon_WhenCallTwice() {
        val listWith100Pokemon = createPokemonList(1, 100)
        val newListWith100Pokemon = createPokemonList(2, 100)
        val resultArray: List<Pokemon> = listWith100Pokemon + newListWith100Pokemon

        runBlocking {
            `when`(service.listAll(0)).thenReturn(listWith100Pokemon)
            `when`(service.listAll(100)).thenReturn(newListWith100Pokemon)

            val items: LiveData<List<Pokemon>> = pokemonClientService.items()
            pokemonClientService.listItems()
            pokemonClientService.listItems()

            assertThat(items.value, equalTo(resultArray))
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