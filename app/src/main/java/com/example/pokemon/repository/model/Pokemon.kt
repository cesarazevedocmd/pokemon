package com.example.pokemon.repository.model

data class Pokemon(
    var name: String,
    val frontPhotoUrl: String,
    val backPhotoUrl: String,
    val weight: Int,
    val height: Int,
    var stats: List<String> = listOf(),
    var abilities: List<String> = listOf(),
    var types: List<String> = listOf(),
)