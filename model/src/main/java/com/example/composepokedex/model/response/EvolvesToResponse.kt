package com.example.composepokedex.model.response

data class EvolvesToResponse(
    val evolves_to: List<EvolvesToResponse>,
    val species: SpeciesResponse
)