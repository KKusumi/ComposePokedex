package com.example.composepokedex.domain

import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.view.PokemonListView
import com.example.composepokedex.repository.PokemonListViewRepository
import javax.inject.Inject
import com.github.michaelbull.result.Result

interface GetPokemonListViewUseCase {
    suspend fun execute(): Result<PokemonListView, PokeDexException>
}

internal class GetPokemonListViewUseCaseImpl @Inject constructor(
    private val pokemonListViewRepository: PokemonListViewRepository
): GetPokemonListViewUseCase {
    override suspend fun execute(): Result<PokemonListView, PokeDexException> {
        return pokemonListViewRepository.fetchData()
    }
}
