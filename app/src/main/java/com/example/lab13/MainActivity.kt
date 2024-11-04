package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                AnimatedContentExample()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var state by remember { mutableStateOf<ContentState>(ContentState.Loading) }

    Column {
        // AnimatedContent to switch between states
        AnimatedContent(
            targetState = state,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
            }
        ) { targetState ->
            when (targetState) {
                ContentState.Loading -> {
                    Text(text = "Cargando...", modifier = Modifier.padding(16.dp))
                }
                ContentState.Content -> {
                    Text(text = "Contenido cargado con éxito!", modifier = Modifier.padding(16.dp))
                }
                ContentState.Error -> {
                    Text(text = "Ha ocurrido un error!", modifier = Modifier.padding(16.dp))
                }
            }
        }

        // Buttons to change the state
        Button(onClick = { state = ContentState.Loading }) {
            Text("Cargar")
        }
        Button(onClick = { state = ContentState.Content }) {
            Text("Mostrar Contenido")
        }
        Button(onClick = { state = ContentState.Error }) {
            Text("Mostrar Error")
        }
    }
}

// Define the states
sealed class ContentState {
    object Loading : ContentState()
    object Content : ContentState()
    object Error : ContentState()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab13Theme {
        AnimatedContentExample()
    }
}