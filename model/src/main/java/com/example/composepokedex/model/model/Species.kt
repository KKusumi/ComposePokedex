package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.SpeciesResponse

data class Species(
    val name: String
) {

    companion object {
        fun transform(species: SpeciesResponse): Species {
            return Species(name = species.name)
        }
    }
}