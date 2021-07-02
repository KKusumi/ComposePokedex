package com.example.composepokedex.model.response

data class ChainResponse(
    val evolves_to: List<EvolvesToResponse>,
    val species_response: SpeciesResponse
)