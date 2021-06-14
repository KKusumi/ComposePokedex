package com.example.composepokedex

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PokeDexNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = PokeDexDestination.HOME.name) {

    }
}