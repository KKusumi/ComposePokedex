package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.PokemonSpeciesResponse

data class PokemonSpecies(
    val evolutionChain: EvolutionChain
) {

    companion object {
        fun transform(pokemonSpeciesResponse: PokemonSpeciesResponse): PokemonSpecies {
            return PokemonSpecies(
                evolutionChain = EvolutionChain.transform(pokemonSpeciesResponse.evolution_chain)
            )
        }
    }
}