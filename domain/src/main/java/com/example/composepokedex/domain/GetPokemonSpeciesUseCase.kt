package com.example.composepokedex.domain

import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.PokemonSpecies
import com.example.composepokedex.repository.PokemonSpeciesRepository
import com.github.michaelbull.result.Result

interface GetPokemonSpeciesUseCase {
    suspend fun execute(id: Int): Result<PokemonSpecies, PokeDexException>
}

internal class GetPokemonSpeciesUseCaseImpl(
    private val pokemonSpeciesRepository: PokemonSpeciesRepository
) : GetPokemonSpeciesUseCase {

    override suspend fun execute(id: Int): Result<PokemonSpecies, PokeDexException> {
        return pokemonSpeciesRepository.fetchData(id)
    }
}