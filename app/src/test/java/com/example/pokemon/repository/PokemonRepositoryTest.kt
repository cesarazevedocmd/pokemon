package com.example.pokemon.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemon.createPokemonList
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
    private lateinit var pokemonService: PokemonService

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        val pokemonClientService = PokemonClientService(pokemonService)
        pokemonRepository = PokemonRepository(pokemonClientService)
    }

    @Test
    fun should_ReturnAnEmptyPokemonList_WhenCallItems() {
        val liveData = pokemonRepository.items()

        assertThat(liveData.value, equalTo(listOf()))
    }

    @Test
    fun should_ReturnAListWith100Pokemon_WhenCallListItemOnce() {
        val listWith100Pokemon = createPokemonList(0, 100)

        runBlocking {
            `when`(pokemonService.listItems(anyInt(), anyString())).thenReturn(listWith100Pokemon)

            pokemonRepository.listItems()

            assertThat(pokemonRepository.items().value, equalTo(listWith100Pokemon))
        }
    }
}