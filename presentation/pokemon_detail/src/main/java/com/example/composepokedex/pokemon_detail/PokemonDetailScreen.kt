package com.example.composepokedex.pokemon_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.composepokedex.model.model.EvolutionChain
import com.example.composepokedex.model.model.Type
import com.example.composepokedex.model.view.PokemonDetailView
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class
)// OptIn使うとAnnotation付けなきゃいけないのがここだけになる
@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(),
    number: Int,
    onClickNextPokemon: (Int) -> Unit,
    onClickBack: () -> Unit
) {
    val lifecycleObserver = remember(pokemonDetailViewModel) {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                pokemonDetailViewModel.fetchEvolutionData(number = number)
                pokemonDetailViewModel.fetchPokemonDetail(number = number)
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
    val evolutionChain by pokemonDetailViewModel.evolutionChain.observeAsState(EvolutionChain.getEmpty())

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            PokemonDetailBottomSheetContent(evolutionList = evolutionChain.getEvolutionList()) {
                onClickNextPokemon.invoke(it)
            }
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetBackgroundColor = Color(0xfff5f5f5)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xfff5f5f5))
        ) {
            val (container, halfCircle, backArrow, evolution) = createRefs()
            val verticalScrollState = rememberScrollState()

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
                        request = pokemonDetailView.imageUrl
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
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
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
        number = 0,
        onClickNextPokemon = {},
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