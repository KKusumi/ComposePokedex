package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.PokemonSpeciesResponse

data class PokemonSpecies(
    val evolutionChainUrl: String
) {

    // https://pokeapi.co/api/v2/evolution-chain/213/
    val evolutionChainId: Int get() = evolutionChainUrl.removeSuffix("/").split("/").last().toInt()

    companion object {
        fun transform(pokemonSpeciesResponse: PokemonSpeciesResponse): PokemonSpecies {
            return PokemonSpecies(
                evolutionChainUrl = pokemonSpeciesResponse.evolution_chain?.url ?: ""
            )
        }
    }
}