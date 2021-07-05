package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepokedex.model.view.PokemonDetailView

@Composable
fun PokemonDetailInfoPage2(
    pokemonDetailView: PokemonDetailView
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonDetailStatusBarCard(label = "HP", value = "${pokemonDetailView.hp}")

        PokemonDetailStatusBarCard(label = "Attack", value = "${pokemonDetailView.attack}")

        PokemonDetailStatusBarCard(label = "Defense", value = "${pokemonDetailView.defense}")

        PokemonDetailStatusBarCard(label = "Special Attack", value = "${pokemonDetailView.special_attack}")

        PokemonDetailStatusBarCard(label = "Special Defense", value = "${pokemonDetailView.special_defense}")

        PokemonDetailStatusBarCard(label = "Speed", value = "${pokemonDetailView.speed}")
    }
}