package com.example.composepokedex.repository.di

import com.example.composepokedex.remote.PokeApiClient
import com.example.composepokedex.repository.PokemonDetailViewRepository
import com.example.composepokedex.repository.PokemonDetailViewRepositoryImpl
import com.example.composepokedex.repository.PokemonListViewRepository
import com.example.composepokedex.repository.PokemonListViewRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonListViewRepository(
        pokeApiClient: PokeApiClient
    ): PokemonListViewRepository {
        return PokemonListViewRepositoryImpl(pokeApiClient)
    }

    @Provides
    @Singleton
    fun providePokemonDetailViewRepository(
        pokeApiClient: PokeApiClient
    ): PokemonDetailViewRepository {
        return PokemonDetailViewRepositoryImpl(pokeApiClient)
    }
}