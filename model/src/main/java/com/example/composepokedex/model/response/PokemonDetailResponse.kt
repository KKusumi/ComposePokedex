package com.example.composepokedex.model.response

data class PokemonDetailResponse(
    val abilities: List<AbilityResponse>,
    val height: Float,
    val id: Int,
    val name: String?,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val weight: Float
) {

    val hp = stats.find { it.stat.name == "hp" }

    val attack = stats.find { it.stat.name == "attack" }

    val defense = stats.find { it.stat.name == "defense" }

    val specialAttack = stats.find { it.stat.name == "special-attack" }

    val specialDefense = stats.find { it.stat.name == "special-defense" }

    val speed = stats.find { it.stat.name == "speed" }
}