package com.example.composepokedex.repository

import com.example.composepokedex.model.model.EmptyResponseBodyException
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.PokemonSpecies
import com.example.composepokedex.remote.PokeApiClient
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import javax.inject.Inject

interface PokemonSpeciesRepository {
    suspend fun fetchData(id: Int): Result<PokemonSpecies, PokeDexException>
}

internal class PokemonSpeciesRepositoryImpl @Inject constructor(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonSpeciesRepository {

    override suspend fun fetchData(id: Int): Result<PokemonSpecies, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonSpecies(id) }.flatMap {
            if (it != null) {
                Ok(PokemonSpecies.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}