package com.example.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.ui.viewmodel.provider.CoroutineContextProvider
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class SuperViewModel(contextProvider: CoroutineContextProvider) : ViewModel() {

    private val coroutineContext: CoroutineContext = contextProvider.IO

    fun launch(function: suspend () -> Unit) {
        viewModelScope.launch(coroutineContext) {
            function()
        }
    }
}