package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.EvolvesToResponse

data class EvolvesTo(
    val evolvesTo: List<EvolvesTo>,
    val species: Species
) {

    companion object {
        fun transform(evolvesTo: EvolvesToResponse): EvolvesTo {
            return EvolvesTo(
                evolvesTo = evolvesTo.evolves_to.map { transform(it) },
                species = Species.transform(evolvesTo.species)
            )
        }
    }
}