package com.example.pokemon.repository.remote.response

data class ListResponse(val count: Int, val next: String, val previous: String, val results: List<ItemListResponse>)

data class ItemListResponse(val name: String, val url: String)