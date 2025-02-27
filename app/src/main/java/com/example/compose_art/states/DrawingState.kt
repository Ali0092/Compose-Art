package com.example.compose_art.states

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.example.compose_art.model.PathData

data class DrawingState(
    val currentPath: PathData? = null,
    val selectedColor: Color = Color.Black,
    val paths: List<PathData> = emptyList(),
    val selectedBrush: ImageBitmap? = null
)