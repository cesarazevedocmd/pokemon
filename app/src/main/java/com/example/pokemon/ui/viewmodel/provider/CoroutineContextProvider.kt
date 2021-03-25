package com.example.pokemon.ui.viewmodel.provider

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
}