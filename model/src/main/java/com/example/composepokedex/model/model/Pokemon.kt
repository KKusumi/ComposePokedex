package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.PokemonResponse

data class Pokemon(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(pokemonResponse: PokemonResponse): Pokemon {
            return Pokemon(
                name = pokemonResponse.name,
                url = pokemonResponse.url
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