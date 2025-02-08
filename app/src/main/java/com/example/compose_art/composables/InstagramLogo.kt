package com.example.compose_art.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun InstagramLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.background(color = Color.White)) {

        val rectWidth = 150f  // Rectangle width
        val rectHeight = 150f // Rectangle height

        val left = (size.width - rectWidth) / 2
        val top = (size.height - rectHeight) / 2
        val left2 = size.width / 2 + 40
        val top2 = size.height / 2 - 40

        // Define gradient colors (similar to Instagram's logo)
        val gradientColors = listOf(
            Color.Red, Color.Blue
        )

        // Create a linear gradient shader
        val gradientBrush = Brush.horizontalGradient(
            colors = gradientColors, tileMode = TileMode.Clamp
        )

        //outer circle
        drawCircle(brush = gradientBrush, radius = 150f)
        //inner most circle
        drawCircle(
            color = Color.White, radius = 25f, style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
        //side dot circle
        drawCircle(color = Color.White, radius = 10f, style = Fill, center = Offset(left2, top2))
        //main rect
        drawRoundRect(
            color = Color.White,
            size = Size(rectWidth, rectHeight),
            topLeft = Offset(left, top),
            style = Stroke(width = 10f, cap = StrokeCap.Round),
            cornerRadius = CornerRadius(15f, 15f)
        )

    }
}
