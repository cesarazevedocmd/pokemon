package com.example.pokemon.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemon.createPokemonList
import com.example.pokemon.repository.model.Pokemon
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonClientServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var pokemonClientService: PokemonClientService

    @Mock
    private lateinit var service: PokemonService

    @Before
    fun setUp() {
        pokemonClientService = PokemonClientService(service)
    }

    @Test
    fun should_ReturnEmptyList_WhenCallItems() {
        val items = pokemonClientService.items()

        assertThat(items.value, equalTo(listOf()))
    }

    @Test
    fun should_ReturnAListWith100Pokemon_WhenCallListItemOnce() {
        val listWith100Pokemon = createPokemonList(1, 100)

        runBlocking {
            `when`(service.listItems(eq(0), anyString())).thenReturn(listWith100Pokemon)

            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value, equalTo(listWith100Pokemon))
        }
    }

    @Test
    fun should_ReturnAListWith200Pokemon_WhenCallListItemTwice() {
        val listWith100Pokemon = createPokemonList(1, 100)
        val newListWith100Pokemon = createPokemonList(2, 100)
        val resultArray: List<Pokemon> = listWith100Pokemon + newListWith100Pokemon

        runBlocking {
            `when`(service.listItems(eq(0), anyString())).thenReturn(listWith100Pokemon)
            `when`(service.listItems(eq(100), anyString())).thenReturn(newListWith100Pokemon)

            pokemonClientService.listItems()
            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value, equalTo(resultArray))
        }
    }

    @Test
    fun should_ReturnAListWith300Pokemon_WhenCallListItemThreeTimes() {
        runBlocking {
            `when`(service.listItems(anyInt(), anyString())).thenReturn(createPokemonList(0, 100))

            pokemonClientService.listItems()
            pokemonClientService.listItems()
            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value?.size, equalTo(300))
        }
    }
}