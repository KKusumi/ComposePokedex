package com.example.composepokedex.pokemon_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(),
    number: Int
) {
    pokemonDetailViewModel.fetchData(number)

    Text("Hello World!")
}