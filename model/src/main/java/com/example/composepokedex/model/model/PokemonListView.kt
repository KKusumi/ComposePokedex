package com.example.composepokedex.model.model

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

    data class Pokemon(
        val name: String,
        val url: String
    ) {
        companion object {
            fun transform(pokemon: PokemonListResponse.Pokemon): Pokemon {
                return Pokemon(
                    name = pokemon.name,
                    url = pokemon.url
                )
            }
        }

        val number
            get() = generatePokemonNumber(url)

        val thumbnailImageUrl get() = generateThumbnailImageUrl(number.toString())

        // PokemonListResponse.Pokemon.urlから図鑑ナンバーを生成する。
        private fun generatePokemonNumber(url: String) =
            url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()

        private fun generateThumbnailImageUrl(number: String) =
            "https://github.com/fanzeyi/pokemon.json/blob/master/thumbnails/${
                number.padStart(
                    3,
                    '0'
                )
            }.png?raw=true"
    }
}