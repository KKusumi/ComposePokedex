package com.example.composepokedex.remote

import com.example.composepokedex.model.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun pokemon(
        @Query("limit") limit: Int
    ): Response<PokemonListResponse>

}