package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composepokedex.model.model.ChainLink
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun PokemonDetailEvolutionCard(
    species: ChainLink.Species,
    onClickNextPokemon: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .width(248.dp)
            .wrapContentHeight()
            .clickable { onClickNextPokemon.invoke(species.id) },
        shape = RoundedCornerShape(100),
        elevation = 1.dp,
    ) {
        ConstraintLayout {
            val (image, number, name) = createRefs()
            Image(
                painter = rememberCoilPainter(request = species.thumbnailImageUrl),
                contentDescription = "thumbnail",
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .constrainAs(image) {
                        top.linkTo(anchor = parent.top, margin = 8.dp)
                        start.linkTo(anchor = parent.start, margin = 16.dp)
                        bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                    }
            )
            Text(
                text = "No.${species.id.toString().padStart(3, '0')}",
                fontSize = 11.sp,
                lineHeight = 13.sp,
                modifier = Modifier.constrainAs(number) {
                    top.linkTo(anchor = image.top)
                    linkTo(
                        start = image.end,
                        end = parent.end,
                        startMargin = 24.dp,
                        endMargin = 24.dp,
                        bias = 0f
                    )
                }
            )
            Text(
                text = species.name.replaceFirstChar { it.uppercase() },
                fontSize = 17.sp,
                lineHeight = 22.sp,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(anchor = number.bottom, margin = 4.dp)
                    linkTo(start = number.start, end = parent.end, endMargin = 24.dp, bias = 0f)
                }
            )
        }
    }
}