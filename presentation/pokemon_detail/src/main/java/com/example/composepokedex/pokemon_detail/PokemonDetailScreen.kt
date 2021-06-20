package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepokedex.model.view.PokemonDetailView
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(),
    number: String
) {
    val lifecycleObserver = remember(pokemonDetailViewModel) {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                pokemonDetailViewModel.fetchData(number = number.toInt())
            }
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    val pokemonDetailView by pokemonDetailViewModel.pokemonDetailView.observeAsState(
        PokemonDetailView.getEmptyInstance()
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (image, numberText, nameText) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 48.dp, start = 48.dp, end = 48.dp)
                    .aspectRatio(1.0f),
                painter = rememberCoilPainter(
                    request = pokemonDetailView?.imageUrl
                ),
                contentDescription = pokemonDetailView.name,
            )
            Text(
                text = "No.${pokemonDetailView.id}",
                fontSize = 16.sp,
                lineHeight = 13.sp,
                modifier = Modifier
                    .constrainAs(numberText) {
                        top.linkTo(anchor = image.bottom, margin = 8.dp)
                        start.linkTo(anchor = parent.start, margin = 16.dp)
                    }
            )
            Text(
                text = pokemonDetailView.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp,
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(anchor = numberText.bottom, margin = 8.dp)
                        start.linkTo(anchor = parent.start, margin = 16.dp)
                    }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
        ) {
            val typeText = if (pokemonDetailView.type2 != null) {
                "${pokemonDetailView.type1.type.name}・${pokemonDetailView.type2?.type?.name}"
            } else {
                pokemonDetailView.type1.type.name
            }
            StatusBarCard(label = "Type", value = typeText)
            Spacer(modifier = Modifier.height(8.dp))

            StatusBarCard(label = "Height", value = "${pokemonDetailView.height / 10}m")
            Spacer(modifier = Modifier.height(8.dp))

            StatusBarCard(label = "Weight", value = "${pokemonDetailView.weight / 10}kg")
            Spacer(modifier = Modifier.height(8.dp))

            StatusBarCard(label = "Ability 1", value = "${pokemonDetailView.ability1.ability.name}")
            Spacer(modifier = Modifier.height(8.dp))

            val ability2Text = pokemonDetailView.ability2?.ability?.name ?: ""
            StatusBarCard(label = "Ability 2", value = ability2Text)
            Spacer(modifier = Modifier.height(8.dp))

            val hiddenAbility = pokemonDetailView.hiddenAbility?.ability?.name ?: ""
            StatusBarCard(label = "HiddenAbility", value = hiddenAbility)
        }
    }

}

@Composable
fun StatusBarCard(
    label: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(16.dp)
        ) {
            val (labelText, valueText) = createRefs()
            Text(
                text = label,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                modifier = Modifier.constrainAs(labelText) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    start.linkTo(anchor = parent.start)
                }
            )
            Text(
                text = value,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                modifier = Modifier.constrainAs(valueText) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = parent.end)
                }
            )
        }
    }
}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(
        pokemonDetailViewModel = getFakePokemonDetailViewModel(),
        number = "0"
    )
}