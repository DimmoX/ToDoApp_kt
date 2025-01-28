package com.example.todoapp.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwipeHintIcon(direction: String, modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    if(direction == "right"){
        val offsetX by infiniteTransition.animateFloat(
            initialValue = 20f,
            targetValue = -25f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Icon(
            imageVector = Icons.Rounded.TouchApp,
            contentDescription = "deslizar a la derecha",
            modifier
                .size(75.dp)
                .offset(x = offsetX.dp),
            tint = Color(0xFFeceaed).copy(alpha = 0.7f)
        )
    } else if(direction == "left"){
        val offsetX by infiniteTransition.animateFloat(
            initialValue = -20f,
            targetValue = 25f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Icon(
            imageVector = Icons.Rounded.TouchApp,
            contentDescription = "deslizar aa la izquierda",
            modifier
                .size(75.dp)
                .offset(x = offsetX.dp),
            tint = Color(0xFFeceaed).copy(alpha = 0.7f)
        )

    }
}
