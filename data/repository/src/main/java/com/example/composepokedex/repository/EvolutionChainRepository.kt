package com.example.composepokedex.repository

import com.example.composepokedex.model.model.EmptyResponseBodyException
import com.example.composepokedex.model.model.EvolutionChain
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.remote.PokeApiClient
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface EvolutionChainRepository {
    suspend fun fetchData(id: Int): Result<EvolutionChain, PokeDexException>
}

internal class EvolutionChainRepositoryImpl(
    private val pokeApiClient: PokeApiClient
): ApiRepository(), EvolutionChainRepository {

    override suspend fun fetchData(id: Int): Result<EvolutionChain, PokeDexException> {
        return execute { pokeApiClient.fetchEvolutionChain(id) }.flatMap {
            if (it != null) {
                Ok(EvolutionChain.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}