package com.example.compose_art.utils

import androidx.compose.ui.graphics.Color
import com.example.compose_art.R
import com.example.compose_art.model.BrushData

object Utils {

    val colorsList = mutableListOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.White,
        Color.Blue,
        Color.DarkGray,
        Color.Magenta,
        Color.Black,
        Color.Cyan,
        Color.LightGray,
        Color.Gray
    )

    val brushList = mutableListOf(
        BrushData(brushPlaceHolder = R.drawable.b0, brushResource = null),
        BrushData(brushPlaceHolder = R.drawable.b1_placeholder, brushResource = R.drawable.b1),
        BrushData(brushPlaceHolder = R.drawable.b2_placeholder, brushResource = R.drawable.b2),
        BrushData(brushPlaceHolder = R.drawable.b3_placeholder, brushResource = R.drawable.b3),
        BrushData(brushPlaceHolder = R.drawable.b4_placeholder, brushResource = R.drawable.b4),
        BrushData(brushPlaceHolder = R.drawable.b5_placeholder, brushResource = R.drawable.b5),
        BrushData(brushPlaceHolder = R.drawable.b6_placeholder, brushResource = R.drawable.b6),
        BrushData(brushPlaceHolder = R.drawable.b7_placeholder, brushResource = R.drawable.b7),
        BrushData(brushPlaceHolder = R.drawable.b8_placeholder, brushResource = R.drawable.b8),
        BrushData(brushPlaceHolder = R.drawable.b9_placeholder, brushResource = R.drawable.b9),
        BrushData(brushPlaceHolder = R.drawable.b10_placeholder, brushResource = R.drawable.b10),
    )

}