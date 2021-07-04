package com.example.composepokedex.model.response

data class ChainLinkResponse(
    val species: PokemonSpeciesResponse,
    val evolves_to: List<ChainLinkResponse>,
)