package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationExamples()
        }
    }
}

@Composable
fun AnimationExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        // Ejercicio 1: Animación de Color
        var isBlue by remember { mutableStateOf(true) }
        val backgroundColor = animateColorAsState(if (isBlue) Color.Blue else Color.Red)

        Button(onClick = { isBlue = !isBlue }) {
            Text("Cambiar Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(backgroundColor.value)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Ejercicio 2: Animación de Visibilidad
        var isVisible by remember { mutableStateOf(true) }

        Button(onClick = { isVisible = !isVisible }) {
            Text("Toggle Visibilidad")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = isVisible) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Ejercicio 3: Animación de Tamaño y Posición
        var size by remember { mutableStateOf(100.dp) }
        var offsetX by remember { mutableStateOf(0.dp) }
        var offsetY by remember { mutableStateOf(0.dp) }

        val animatedSize: Dp by animateDpAsState(targetValue = size)
        val animatedOffsetX: Dp by animateDpAsState(targetValue = offsetX)
        val animatedOffsetY: Dp by animateDpAsState(targetValue = offsetY)

        Button(onClick = {
            size = if (size == 100.dp) 200.dp else 100.dp
            offsetX = if (offsetX == 0.dp) 100.dp else 0.dp
            offsetY = if (offsetY == 0.dp) 100.dp else 0.dp
        }) {
            Text("Mover y Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(animatedSize)
                .offset(animatedOffsetX, animatedOffsetY)
                .background(Color.Blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationExamples()
}
