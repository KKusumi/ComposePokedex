package com.example.composepokedex

enum class PokeDexDestination {

    HOME,
    POKEMON_DETAIL;

    companion object {
        fun fromRoute(route: String?): PokeDexDestination {
            return when (route?.substringBefore("/")) {
                HOME.name -> HOME
                POKEMON_DETAIL.name -> POKEMON_DETAIL
                else -> HOME
            }
        }
    }
}
