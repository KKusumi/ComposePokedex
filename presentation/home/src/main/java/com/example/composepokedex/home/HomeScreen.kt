package com.example.composepokedex.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepokedex.model.model.PokemonListView
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val pokemonListView by homeViewModel.pokemonListView.observeAsState(PokemonListView.getEmptyInstance())
    LazyColumn(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
    ) {
        items(items = pokemonListView.pokemons) { pokemon ->
            HomeListItem(pokemon = pokemon)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(homeViewModel = getFakeHomeViewModel())
}

@Composable
fun HomeListItem(
    pokemon: PokemonListView.Pokemon
) {
    Row {
        Surface {
            Image(
                modifier = Modifier.padding(16.dp),
                painter = rememberCoilPainter(
                    request = pokemon.thumbnailImageUrl
                ),
                contentDescription = pokemon.name
            )
            Text(
                text = pokemon.name,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
