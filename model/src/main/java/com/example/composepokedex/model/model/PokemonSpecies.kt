package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.PokemonSpeciesResponse

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val evolutionChainUrl: String
) {

    // https://pokeapi.co/api/v2/evolution-chain/213/
    val evolutionChainId: Int get() = evolutionChainUrl.split("/").last().toInt()

    companion object {
        fun transform(pokemonSpeciesResponse: PokemonSpeciesResponse): PokemonSpecies {
            return PokemonSpecies(
                id = pokemonSpeciesResponse.id,
                name = pokemonSpeciesResponse.name,
                evolutionChainUrl = pokemonSpeciesResponse.evolution_chain
            )
        }
    }
}