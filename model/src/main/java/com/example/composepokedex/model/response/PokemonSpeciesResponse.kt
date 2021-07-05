package com.example.composepokedex.model.response

data class PokemonSpeciesResponse(
    val evolution_chain: EvolutionChain?
) {
    class EvolutionChain(
        val url: String
    )
}