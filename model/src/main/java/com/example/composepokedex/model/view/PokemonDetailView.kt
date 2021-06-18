package com.example.composepokedex.model.view

import com.example.composepokedex.model.model.Ability
import com.example.composepokedex.model.model.Type
import com.example.composepokedex.model.response.PokemonDetailResponse

data class PokemonDetailView(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
    val abilities: List<Ability>,
) {

    companion object {
        fun transform(pokemonDetailResponse: PokemonDetailResponse): PokemonDetailView {
            return PokemonDetailView(
                id = pokemonDetailResponse.id,
                name = pokemonDetailResponse.name ?: "",
                height = pokemonDetailResponse.height,
                weight = pokemonDetailResponse.weight,
                types = pokemonDetailResponse.types.map { Type.transform(it) },
                abilities = pokemonDetailResponse.abilities.map { Ability.transform(it) }
            )
        }
    }
}