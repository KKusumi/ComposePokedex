package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepokedex.model.view.PokemonDetailView

@Composable
fun PokemonDetailInfoPage1(
    pokemonDetailView: PokemonDetailView
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val typeText = if (pokemonDetailView.type2 != null) {
            "${pokemonDetailView.type1.name}・${pokemonDetailView.type2?.name}"
        } else {
            pokemonDetailView.type1.name
        }
        PokemonDetailStatusBarCard(label = "Type", value = typeText)

        PokemonDetailStatusBarCard(label = "Height", value = "${pokemonDetailView.height / 10}m")

        PokemonDetailStatusBarCard(label = "Weight", value = "${pokemonDetailView.weight / 10}kg")

        PokemonDetailStatusBarCard(label = "Ability 1", value = "${pokemonDetailView.ability1.ability.name}")

        val ability2Text = pokemonDetailView.ability2?.ability?.name ?: ""
        PokemonDetailStatusBarCard(label = "Ability 2", value = ability2Text)

        val hiddenAbility = pokemonDetailView.hiddenAbility?.ability?.name ?: ""
        PokemonDetailStatusBarCard(label = "HiddenAbility", value = hiddenAbility)
    }
}