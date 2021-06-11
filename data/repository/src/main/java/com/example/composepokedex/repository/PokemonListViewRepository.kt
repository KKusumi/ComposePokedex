package com.example.composepokedex.repository

import com.example.composepokedex.model.model.EmptyResponseBodyException
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.PokemonListView
import com.example.composepokedex.remote.PokeApiClient
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import javax.inject.Inject

interface PokemonListViewRepository {
    suspend fun fetchData(): Result<PokemonListView, PokeDexException>
}

internal class PokemonListViewRepositoryImpl @Inject constructor(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonListViewRepository {

    override suspend fun fetchData(): Result<PokemonListView, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonList() }.flatMap {
            if (it != null) {
                Ok(PokemonListView.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}