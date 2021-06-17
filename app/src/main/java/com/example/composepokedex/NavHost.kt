package com.example.composepokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composepokedex.home.HomeScreen
import com.example.composepokedex.home.HomeViewModel

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
            HomeScreen(homeViewModel)
        }
    }
}