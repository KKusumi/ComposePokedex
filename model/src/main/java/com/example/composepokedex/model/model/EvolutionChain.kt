package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.EvolutionChainResponse

data class EvolutionChain(
    val chain: ChainLink,
    val id: Int
) {

    companion object {
        fun transform(evolutionChain: EvolutionChainResponse): EvolutionChain {
            return EvolutionChain(
                chain = ChainLink.transform(evolutionChain.chain),
                id = evolutionChain.id
            )
        }
    }
}