package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.EvolutionChainResponse

data class EvolutionChain(
    val chain: ChainLink,
    val id: Int
) {

    fun getEvolutionList(): List<ChainLink.Species> {
        val list = mutableListOf(chain.species)
        chain.evolvesTo.forEach {
            list.add(it.species)
            it.evolvesTo.forEach {
                list.add(it.species)
            }
        }
        return list
    }

    companion object {
        fun transform(evolutionChain: EvolutionChainResponse): EvolutionChain {
            return EvolutionChain(
                chain = ChainLink.transform(evolutionChain.chain),
                id = evolutionChain.id
            )
        }
        fun getEmpty(): EvolutionChain {
            return EvolutionChain(
                chain = ChainLink.getEmpty(),
                id = 0
            )
        }
    }
}