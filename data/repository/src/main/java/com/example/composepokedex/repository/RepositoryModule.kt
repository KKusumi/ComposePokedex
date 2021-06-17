package com.example.composepokedex.repository

import com.example.composepokedex.remote.PokeApiClient
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
}