package com.example.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composepokedex.ui.theme.ComposePokeDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePokeDexApp()
        }
    }
}

@Preview
@Composable
fun ComposePokeDexApp() {
    ComposePokeDexTheme {
        val navController = rememberNavController()
        Scaffold(
            backgroundColor = Color(0xfff5f5f5)
        ) {
            PokeDexNavHost(navController = navController)
        }
    }
}