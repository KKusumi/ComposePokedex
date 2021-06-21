package com.example.composepokedex.model.response

data class PokemonDetailResponse(
    val abilities: List<AbilityResponse>,
    val height: Float,
    val id: Int,
    val name: String?,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val weight: Float
)