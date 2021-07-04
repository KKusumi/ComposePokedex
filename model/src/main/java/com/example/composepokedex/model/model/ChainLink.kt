package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.ChainLinkResponse

data class ChainLink(
    val species: PokemonSpecies,
    val evolvesTo: List<ChainLink>,
) {

    companion object {
        fun transform(chainLink: ChainLinkResponse): ChainLink {
            return ChainLink(
                evolvesTo = chainLink.evolves_to.map { transform(it) },
                species = PokemonSpecies.transform(chainLink.species)
            )
        }
    }
}