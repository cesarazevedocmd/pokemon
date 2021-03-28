package com.example.pokemon.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemon.createListResponse
import com.example.pokemon.createPokemonList
import com.example.pokemon.createPokemonResponse
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

        assertThat(items.value?.data, equalTo(listOf()))
    }

    @Test
    fun should_ReturnAListWith100Pokemon_WhenCallListItemsOnce() {
        val pokemonList = createPokemonList(quantity = 100)
        val listResponse = createListResponse(quantity = 100)

        runBlocking {
            `when`(service.listItems(anyString(), eq(0))).thenReturn(listResponse)
            `when`(service.findPokemon(anyString(), anyString())).thenReturn(createPokemonResponse())

            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value?.data, equalTo(pokemonList))
        }
    }

    @Test
    fun should_ReturnAListWith200Pokemon_WhenCallListItemsTwice() {
        val listResponse = createListResponse(quantity = 100)
        val pokemonList = createPokemonList(quantity = 100)
        val result = pokemonList + pokemonList

        runBlocking {
            `when`(service.listItems(anyString(), anyInt())).thenReturn(listResponse)
            `when`(service.findPokemon(anyString(), anyString())).thenReturn(createPokemonResponse())

            pokemonClientService.listItems()
            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value?.data, equalTo(result))
        }
    }

    @Test
    fun should_ReturnAListWith300Pokemon_WhenCallListItemsThreeTimes() {
        val pokemonList = createPokemonList(quantity = 300)
        val listResponse = createListResponse(quantity = 100)

        runBlocking {
            `when`(service.listItems(anyString(), anyInt())).thenReturn(listResponse)
            `when`(service.findPokemon(anyString(), anyString())).thenReturn(createPokemonResponse())

            pokemonClientService.listItems()
            pokemonClientService.listItems()
            pokemonClientService.listItems()

            assertThat(pokemonClientService.items().value?.data?.size, equalTo(pokemonList.size))
        }
    }
}