package com.example.composepokedex.domain.di

import com.example.composepokedex.domain.GetPokemonListViewUseCase
import com.example.composepokedex.domain.GetPokemonListViewUseCaseImpl
import com.example.composepokedex.repository.PokemonListViewRepository
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
}