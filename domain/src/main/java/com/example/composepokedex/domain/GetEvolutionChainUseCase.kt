package com.example.composepokedex.domain

import com.example.composepokedex.model.model.EvolutionChain
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.repository.EvolutionChainRepository
import com.github.michaelbull.result.Result
import javax.inject.Inject

interface GetEvolutionChainUseCase {
    suspend fun execute(id: Int): Result<EvolutionChain, PokeDexException>
}

internal class GetEvolutionChainUseCaseImpl @Inject constructor(
    private val evolutionChainRepository: EvolutionChainRepository
) : GetEvolutionChainUseCase {

    override suspend fun execute(id: Int): Result<EvolutionChain, PokeDexException> {
        return evolutionChainRepository.fetchData(id)
    }
}
