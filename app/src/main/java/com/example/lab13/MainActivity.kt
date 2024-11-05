package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                CombinedAnimationsScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CombinedAnimationsScreen() {
    var sizeState by remember { mutableStateOf(100.dp) }
    var colorState by remember { mutableStateOf(Color.Red) }
    var showButton by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    val animatedSize by animateDpAsState(
        targetValue = sizeState,
        animationSpec = tween(durationMillis = 500)
    )
    val animatedColor by animateColorAsState(
        targetValue = colorState,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Elemento que cambia de tamaño y color al hacer clic
            Box(
                modifier = Modifier
                    .size(animatedSize)
                    .background(animatedColor, shape = CircleShape)
                    .clickable {
                        sizeState = if (sizeState == 100.dp) 150.dp else 100.dp
                        colorState = if (colorState == Color.Red) Color.Blue else Color.Red
                    }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón que se desplaza y desaparece usando AnimatedVisibility
            AnimatedVisibility(
                visible = showButton,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Button(onClick = { showButton = false }) {
                    Text("Desplazar y Ocultar")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Transición de contenido entre modo claro y oscuro
            AnimatedContent(targetState = isDarkMode, transitionSpec = {
                fadeIn(animationSpec = tween(700)) with fadeOut(animationSpec = tween(700))
            }) { targetState ->
                Button(onClick = { isDarkMode = !isDarkMode }) {
                    Text(if (targetState) "Modo Claro" else "Modo Oscuro")
                }
            }
        }
    }
}
