package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.EvolutionChainResponse

data class EvolutionChain(
    val chain: Chain,
    val id: Int
) {

    companion object {
        fun transform(evolutionChain: EvolutionChainResponse): EvolutionChain {
            return EvolutionChain(
                chain = Chain.transform(evolutionChain.chain),
                id = evolutionChain.id
            )
        }
    }
}