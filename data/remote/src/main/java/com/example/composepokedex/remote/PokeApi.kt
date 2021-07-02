package com.example.composepokedex.remote

import com.example.composepokedex.model.response.EvolutionChainResponse
import com.example.composepokedex.model.response.PokemonDetailResponse
import com.example.composepokedex.model.response.PokemonListResponse
import com.example.composepokedex.model.response.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun pokemon(
        @Query("limit") limit: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun pokemonDetail(@Path("id") id: Int): Response<PokemonDetailResponse>

    @GET("pokemon-species/{id}")
    suspend fun pokemonSpecies(@Path("id") id: Int): Response<PokemonSpeciesResponse>

    @GET("evolution-chain/{id}")
    suspend fun evolutionChain(@Path("id") id: Int): Response<EvolutionChainResponse>
}