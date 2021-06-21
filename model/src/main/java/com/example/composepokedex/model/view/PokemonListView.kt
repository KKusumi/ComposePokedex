package com.example.composepokedex.model.view

import com.example.composepokedex.model.model.Pokemon
import com.example.composepokedex.model.response.PokemonListResponse

data class PokemonListView(
    val pokemons: List<Pokemon>
) {
    companion object {
        fun transform(pokemonListResponse: PokemonListResponse): PokemonListView {
            return PokemonListView(
                pokemons = pokemonListResponse.results.map { Pokemon.transform(it) }
            )
        }

        fun getEmptyInstance(): PokemonListView {
            return PokemonListView(listOf())
        }
    }
}