package com.example.compose_art.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

data class PathData(
    val path: List<Offset>,
    val color: Color = Color.Black,
    val brush: ImageBitmap? = null
)
