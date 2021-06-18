package com.example.composepokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composepokedex.home.HomeScreen
import com.example.composepokedex.home.HomeViewModel
import com.example.composepokedex.pokemon_detail.PokemonDetailScreen
import com.example.composepokedex.pokemon_detail.PokemonDetailViewModel

@Composable
fun PokeDexNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PokeDexDestination.HOME.name
    ) {
        composable(route = PokeDexDestination.HOME.name) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                homeViewModel = homeViewModel,
                navigateToPokemonDetail = { number ->
                    navController.navigate("${PokeDexDestination.POKEMON_DETAIL}/number")
                }
            )
        }

        composable(route = "${PokeDexDestination.POKEMON_DETAIL}/{number}") {
            it.arguments?.getInt("number")?.let { number ->
                val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
                PokemonDetailScreen(
                    pokemonDetailViewModel = pokemonDetailViewModel,
                    number = number
                )
            }
        }
    }
}