package com.example.composepokedex.model.response

data class ChainLinkResponse(
    val species: Species,
    val evolves_to: List<ChainLinkResponse>,
) {

    data class Species (
        val name: String,
        val url: String
    )
}