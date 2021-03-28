package com.example.pokemon.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemon.createListResponse
import com.example.pokemon.createPokemonList
import com.example.pokemon.createPokemonResponse
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: PokemonService

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        val pokemonClientService = PokemonClientService(service)
        pokemonRepository = PokemonRepository(pokemonClientService)
    }

    @Test
    fun should_ReturnAnEmptyPokemonList_WhenCallItems() {
        val liveData = pokemonRepository.items()

        assertThat(liveData.value?.data, equalTo(listOf()))
    }

    @Test
    fun should_ReturnAListWith100Pokemon_WhenCallListItemsOnce() {
        val pokemonList = createPokemonList(quantity = 100)
        val listResponse = createListResponse(quantity = 100)

        runBlocking {
            `when`(service.listItems(anyString(), anyInt())).thenReturn(listResponse)
            `when`(service.findPokemon(anyString(), anyString())).thenReturn(createPokemonResponse())

            pokemonRepository.listItems()

            assertThat(pokemonRepository.items().value?.data, equalTo(pokemonList))
        }
    }
}