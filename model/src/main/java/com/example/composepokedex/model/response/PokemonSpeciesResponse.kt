package com.example.composepokedex.model.response

data class PokemonSpeciesResponse(
    val id: Int,
    val name: String,
    val evolution_chain: String
)