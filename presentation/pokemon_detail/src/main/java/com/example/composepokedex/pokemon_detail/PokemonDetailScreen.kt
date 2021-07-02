package com.example.composepokedex.pokemon_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepokedex.model.model.Type
import com.example.composepokedex.model.view.PokemonDetailView
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)// OptIn使うとAnnotation付けなきゃいけないのがここだけになる
@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(),
    number: String,
    onClickBack: () -> Unit
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (container, halfCircle, backArrow, evolution) = createRefs()
        val verticalScrollState = rememberScrollState()

        // Half Circle
        Image(
            painter = painterResource(id = getTypeHalfCircle(pokemonDetailView.type1)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "img_type_half_circle",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(halfCircle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(verticalScrollState)
                .constrainAs(container) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 16.dp)
        ) {
            // Main Image
            Image(
                modifier = Modifier
                    .padding(top = 48.dp, start = 48.dp, end = 48.dp)
                    .aspectRatio(1.0f),
                painter = rememberCoilPainter(
                    request = pokemonDetailView?.imageUrl
                ),
                contentDescription = pokemonDetailView.name,
            )

            // Number
            Text(
                text = "No.${pokemonDetailView.id.toString().padStart(3, '0')}",
                fontSize = 17.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Name
            Text(
                text = pokemonDetailView.name.replaceFirstChar { it.uppercase() },
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            val pagerState = rememberPagerState(pageCount = 2, initialOffscreenLimit = 2)

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0xff181818),
                inactiveColor = Color(0xffbdbdbd),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) { page ->
                when (page) {
                    0 -> {
                        PokemonDetailInfoPage1(pokemonDetailView = pokemonDetailView)
                    }
                    1 -> {
                        PokemonDetailInfoPage2(pokemonDetailView = pokemonDetailView)
                    }
                }
            }

        }

        // Back Arrow
        Image(
            painter = painterResource(id = R.drawable.drawable_ic_back_arrow),
            contentDescription = "ic_back_arrow",
            modifier = Modifier
                .constrainAs(backArrow) {
                    top.linkTo(anchor = parent.top, margin = 24.dp)
                    start.linkTo(anchor = parent.start, margin = 24.dp)
                }
                .clickable {
                    onClickBack.invoke()
                }
        )

        // Evolution
        Image(
            painter = painterResource(id = R.drawable.drawable_ic_evolution),
            contentDescription = "ic_evolution",
            modifier = Modifier
                .constrainAs(evolution) {
                    top.linkTo(anchor = parent.top, margin = 24.dp)
                    end.linkTo(anchor = parent.end, margin = 24.dp)
                }
                .clickable {
                    // 進化
                }
        )
    }
}

@Composable
fun PokemonDetailInfoPage1(
    pokemonDetailView: PokemonDetailView
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val typeText = if (pokemonDetailView.type2 != null) {
            "${pokemonDetailView.type1.name}・${pokemonDetailView.type2?.name}"
        } else {
            pokemonDetailView.type1.name
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

@Composable
fun PokemonDetailInfoPage2(
    pokemonDetailView: PokemonDetailView
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StatusBarCard(label = "HP", value = "${pokemonDetailView.hp}")
        Spacer(modifier = Modifier.height(8.dp))

        StatusBarCard(label = "Attack", value = "${pokemonDetailView.attack}")
        Spacer(modifier = Modifier.height(8.dp))

        StatusBarCard(label = "Defense", value = "${pokemonDetailView.defense}")
        Spacer(modifier = Modifier.height(8.dp))

        StatusBarCard(label = "Special Attack", value = "${pokemonDetailView.special_attack}")
        Spacer(modifier = Modifier.height(8.dp))

        StatusBarCard(label = "Special Defense", value = "${pokemonDetailView.special_defense}")
        Spacer(modifier = Modifier.height(8.dp))

        StatusBarCard(label = "Speed", value = "${pokemonDetailView.speed}")
    }
}

@Composable
fun StatusBarCard(
    label: String,
    value: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp),
        shape = RoundedCornerShape(100),
        elevation = 1.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
        ) {
            val (labelText, valueText) = createRefs()
            Text(
                text = label,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                modifier = Modifier.constrainAs(labelText) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    start.linkTo(anchor = parent.start)
                }
            )
            Text(
                text = value,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                modifier = Modifier.constrainAs(valueText) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = parent.end)
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@ExperimentalPagerApi
@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(
        pokemonDetailViewModel = getFakePokemonDetailViewModel(),
        number = "0",
        onClickBack = {}
    )
}

private fun getTypeHalfCircle(type: Type): Int = when (type) {
    is Type.Bug -> R.drawable.drawable_bug_color_half_circle
    is Type.Dark -> R.drawable.drawable_dark_color_half_circle
    is Type.Dragon -> R.drawable.drawable_dragon_color_half_circle
    is Type.Electric -> R.drawable.drawable_electric_color_half_circle
    is Type.Fairy -> R.drawable.drawable_fairy_color_half_circle
    is Type.Fighting -> R.drawable.drawable_fighting_color_half_circle
    is Type.Fire -> R.drawable.drawable_fire_color_half_circle
    is Type.Flying -> R.drawable.drawable_flying_color_half_circle
    is Type.Ghost -> R.drawable.drawable_ghost_color_half_circle
    is Type.Grass -> R.drawable.drawable_grass_color_half_circle
    is Type.Ground -> R.drawable.drawable_ground_color_half_circle
    is Type.Ice -> R.drawable.drawable_ice_color_half_circle
    is Type.Normal -> R.drawable.drawable_normal_color_half_circle
    is Type.Poison -> R.drawable.drawable_poison_color_half_circle
    is Type.Psychic -> R.drawable.drawable_psychic_color_half_circle
    is Type.Rock -> R.drawable.drawable_rock_color_half_circle
    is Type.Steel -> R.drawable.drawable_steel_color_half_circle
    is Type.Water -> R.drawable.drawable_water_color_half_circle
}