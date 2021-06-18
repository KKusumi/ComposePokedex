package com.example.composepokedex.domain

import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.view.PokemonDetailView
import com.example.composepokedex.repository.PokemonDetailViewRepository
import com.github.michaelbull.result.Result
import javax.inject.Inject

interface GetPokemonDetailViewUseCase {
    suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class GetPokemonDetailViewUseCaseImpl @Inject constructor(
    private val pokemonDetailViewRepository: PokemonDetailViewRepository
) : GetPokemonDetailViewUseCase {

    override suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException> {
        return pokemonDetailViewRepository.fetchData(id)
    }
}