package com.example.composepokedex.model.view

import com.example.composepokedex.model.model.Ability
import com.example.composepokedex.model.model.Type
import com.example.composepokedex.model.response.PokemonDetailResponse

data class PokemonDetailView(
    val id: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val type1: Type,
    val type2: Type?,
    val ability1: Ability,
    val ability2: Ability?,
    val hiddenAbility: Ability?
) {

    companion object {
        fun transform(pokemonDetailResponse: PokemonDetailResponse): PokemonDetailView {
            return PokemonDetailView(
                id = pokemonDetailResponse.id,
                name = pokemonDetailResponse.name ?: "",
                height = pokemonDetailResponse.height,
                weight = pokemonDetailResponse.weight,
                type1 = Type.transform(pokemonDetailResponse.types[0]),
                type2 = pokemonDetailResponse.types.getOrNull(1)?.let { Type.transform(it) },
                ability1 = Ability.transform(pokemonDetailResponse.abilities[0]),
                ability2 = pokemonDetailResponse.abilities.getOrNull(1)
                    ?.let { Ability.transform(it) },
                hiddenAbility = pokemonDetailResponse.abilities.find { it.is_hidden }
                    ?.let { Ability.transform(it) }
            )
        }

        fun getEmptyInstance(): PokemonDetailView {
            return PokemonDetailView(
                id = 0,
                name = "",
                height = 0f,
                weight = 0f,
                type1 = Type.getEmpty(),
                type2 = Type.getEmpty(),
                ability1 = Ability.getEmpty(),
                ability2 = Ability.getEmpty(),
                hiddenAbility = Ability.getEmpty()
            )
        }
    }

    val imageUrl get() = generateImageUrl(id.toString())

    private fun generateImageUrl(number: String) =
        "https://github.com/fanzeyi/pokemon.json/blob/master/images/${
            number.padStart(
                3,
                '0'
            )
        }.png?raw=true"
}