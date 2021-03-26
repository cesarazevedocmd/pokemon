package com.example.pokemon.ui.viewmodel.provider

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class TestCoroutineContextProvider : CoroutineContextProvider() {

    private val dispatcherTest = TestCoroutineDispatcher()

    override val IO: CoroutineContext by lazy { dispatcherTest }
}