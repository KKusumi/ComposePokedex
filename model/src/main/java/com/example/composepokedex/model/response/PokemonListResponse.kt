package com.example.composepokedex.model.response

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
) {
    data class Pokemon(
        val name: String,
        val url: String
    )
}