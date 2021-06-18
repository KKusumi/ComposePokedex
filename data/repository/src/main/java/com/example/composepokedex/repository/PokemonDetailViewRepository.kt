package com.example.composepokedex.repository

import com.example.composepokedex.model.model.EmptyResponseBodyException
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.view.PokemonDetailView
import com.example.composepokedex.remote.PokeApiClient
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface PokemonDetailViewRepository {
    suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class PokemonDetailViewRepositoryImpl(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonDetailViewRepository {
    override suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonDetail(id) }.flatMap {
            if (it != null) {
                Ok(PokemonDetailView.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}