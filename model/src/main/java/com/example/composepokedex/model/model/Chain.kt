package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.ChainResponse

data class Chain(
    val evolvesTo: List<EvolvesTo>,
    val speciesResponse: Species
) {

    companion object {
        fun transform(chain: ChainResponse): Chain {
            return Chain(
                evolvesTo = chain.evolves_to.map { EvolvesTo.transform(it) },
                speciesResponse = Species.transform(chain.species_response)
            )
        }
    }
}