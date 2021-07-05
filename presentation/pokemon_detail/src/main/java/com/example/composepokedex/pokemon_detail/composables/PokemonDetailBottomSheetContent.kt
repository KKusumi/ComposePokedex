package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepokedex.model.model.ChainLink.Species

@Composable
fun PokemonDetailBottomSheetContent(
    evolutionList: List<Species>,
    onClickNextPokemon: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight() // Height入れないとクラッシュする The initial value must have an associated anchor.
            .padding(top = 24.dp, bottom = 24.dp),
    ) {
        evolutionList.forEach {
            PokemonDetailEvolutionCard(species = it, onClickNextPokemon = onClickNextPokemon)
        }
    }
}