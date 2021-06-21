package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.EvolutionChainResponse

data class EvolutionChain(
    val url: String
) {

    companion object {
        fun transform(evolutionChainResponse: EvolutionChainResponse): EvolutionChain {
            return EvolutionChain(url = evolutionChainResponse.url)
        }
    }
}