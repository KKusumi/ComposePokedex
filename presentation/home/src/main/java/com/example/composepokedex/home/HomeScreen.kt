package com.example.composepokedex.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepokedex.model.view.PokemonListView
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navigateToPokemonDetail: (Int) -> Unit
) {
    val pokemonListView by homeViewModel.pokemonListView.observeAsState(PokemonListView.getEmptyInstance())
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(items = pokemonListView.pokemons) { pokemon ->
            HomeListItem(
                pokemon = pokemon,
                onClickItem = { navigateToPokemonDetail.invoke(pokemon.number) }
            )
        }
    }
}

@Composable
fun HomeListItem(
    pokemon: PokemonListView.Pokemon,
    onClickItem: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClickItem)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            painter = rememberCoilPainter(
                request = pokemon.thumbnailImageUrl
            ),
            contentDescription = pokemon.name
        )
        Column(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = "No.${pokemon.number}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 11.sp,
                lineHeight = 13.sp
            )

            Text(
                text = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 17.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Divider()
}

@Preview
@Composable
fun HomeListItemPreview() {
    HomeListItem(
        pokemon = PokemonListView.Pokemon(
            name = "bulbasour",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        ),
        onClickItem = {}
    )
}