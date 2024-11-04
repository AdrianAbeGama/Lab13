package com.example.lab13

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimation() {
    var isBlue by remember { mutableStateOf(true) }
    val targetColor = if (isBlue) Color.Blue else Color.Green
    val animatedColor by animateColorAsState(targetColor)

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(animatedColor)
            .clickable { isBlue = !isBlue },
    )
}
