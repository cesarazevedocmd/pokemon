package com.example.pokemon.extension

import com.example.pokemon.util.empty

fun List<String>.formatToString(): String {
    return this.toString().replace("[", empty).replace("]", empty)
}