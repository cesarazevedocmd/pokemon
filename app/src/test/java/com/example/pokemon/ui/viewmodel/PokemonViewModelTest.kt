package com.example.pokemon.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemon.createListResponse
import com.example.pokemon.createPokemonList
import com.example.pokemon.createPokemonResponse
import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.repository.remote.PokemonClientService
import com.example.pokemon.repository.remote.PokemonService
import com.example.pokemon.ui.viewmodel.provider.TestCoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: PokemonService

    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setup() {
        val repository = PokemonRepository(PokemonClientService(service))
        viewModel = PokemonViewModel(repository, TestCoroutineContextProvider())
    }

    @Test
    fun should_ReturnAnEmptyPokemonList_WhenCallItems() {
        val liveData = viewModel.items()

        assertThat(liveData.value?.data, equalTo(listOf()))
    }

    @Test
    fun should_ReturnAListWith100Pokemon_WhenCallListItemsOnce() {
        val pokemonList = createPokemonList(quantity = 100)

        runBlocking {
            `when`(service.listItems(anyString(), anyInt())).thenReturn(createListResponse(100))
            `when`(service.findPokemon(anyString(), anyString())).thenReturn(createPokemonResponse())

            viewModel.listItems()

            assertThat(viewModel.items().value?.data, equalTo(pokemonList))
        }
    }
}