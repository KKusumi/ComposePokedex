package com.example.composepokedex.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepokedex.model.model.PokemonListView

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val pokemonListView by homeViewModel.pokemonListView.observeAsState(PokemonListView.getEmptyInstance())

    LazyColumn {
        homeViewModel.pokemonListView.value?.pokemons?.forEach {
//            Row() {
//
//            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(homeViewModel = getFakeHomeViewModel())
}