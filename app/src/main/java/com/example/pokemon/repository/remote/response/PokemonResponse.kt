package com.example.pokemon.repository.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val name: String,
    val weight: Int,
    val height: Int,
    var abilities: List<Ability>?,
    var stats: List<Stat>?,
    var types: List<Type>?,
    val sprites: Sprit?
)

data class Sprit(
    @SerializedName("front_default") val frontPhoto: String?,
    @SerializedName("back_default") val backPhoto: String?
)

data class Ability(val ability: NameUrl)

data class Stat(val stat: NameUrl)

data class Type(val type: NameUrl)

data class NameUrl(val name: String, val url: String)