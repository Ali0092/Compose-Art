package com.example.compose_art.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun HouseCompose(modifier: Modifier = Modifier) {
    Canvas(modifier.background(color = Color(0xFF0D1B2A))) {

        drawRoundRect(
            color = Color.Yellow,
            size = Size(size.width,400f),
            topLeft = Offset(x = 0f,y = size.height-400)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(150f,220f),
            topLeft = Offset(x = size.width-180f,y = size.height-230)
        )

        drawCircle(
            color = Color.White,
            radius = 10f,
            center = Offset(x = size.width-60f,y = size.height-110)
        )

        drawRoundRect(
            color = Color.DarkGray,
            size = Size(80f,200f),
            topLeft = Offset(x = size.width-150f, y = size.height-600f)
        )

        val trianglePath = Path().apply {
            // Moves to top center position
            moveTo(size.width / 2f, size.height-800f)
            // Add line to bottom right corner
            lineTo(size.width, size.height-400)
            // Add line to bottom left corner
            lineTo(0f, size.height-400)
        }
        drawPath(path = trianglePath, color = Color.Red)

    }
}
