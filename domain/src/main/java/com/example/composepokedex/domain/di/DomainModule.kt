package com.example.composepokedex.domain.di

import com.example.composepokedex.domain.*
import com.example.composepokedex.repository.PokemonDetailViewRepository
import com.example.composepokedex.repository.PokemonListViewRepository
import com.example.composepokedex.repository.PokemonSpeciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetPokemonListViewUseCase(
        pokemonListViewRepository: PokemonListViewRepository
    ): GetPokemonListViewUseCase {
        return GetPokemonListViewUseCaseImpl(pokemonListViewRepository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailViewUseCase(
        pokemonDetailViewRepository: PokemonDetailViewRepository
    ): GetPokemonDetailViewUseCase {
        return GetPokemonDetailViewUseCaseImpl(pokemonDetailViewRepository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonSpeciesUseCase(
        pokemonSpeciesRepository: PokemonSpeciesRepository
    ): GetPokemonSpeciesUseCase {
        return GetPokemonSpeciesUseCaseImpl(pokemonSpeciesRepository)
    }
}