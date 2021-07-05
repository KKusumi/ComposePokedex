package com.example.composepokedex.pokemon_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun PokemonDetailStatusBarCard(
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