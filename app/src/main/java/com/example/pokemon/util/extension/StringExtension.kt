package com.example.pokemon.util.extension

import com.example.pokemon.util.empty

fun List<String>.formatToString(): String {
    return this.toString().replace("[", empty).replace("]", empty)
}