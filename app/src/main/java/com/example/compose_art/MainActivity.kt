package com.example.compose_art

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawingCanvas()
        }
    }
}

data class Line(
    val start: Offset, val end: Offset, val color: Color = Color.Black, val strokeWidth: Dp = 5.dp
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawingCanvas() {

    var points by remember { mutableStateOf<List<Offset>>(emptyList()) }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .pointerInput(Unit) {

            detectDragGestures(onDragStart = { touchPoint ->
                points = listOf(touchPoint)
            }, onDrag = { change, _ ->
                val pointsFromHistory = change.historical
                    .map { it.position }
                    .toTypedArray()
                val newPoints = listOf(*pointsFromHistory, change.position)
                points = points + newPoints
            })

        }) {
        if (points.size > 1) {
            val path = Path().apply {
                val firstPoint = points.first()
                val rest = points.subList(1, points.size - 1)

                moveTo(firstPoint.x, firstPoint.y)
                rest.forEach {
                    lineTo(it.x, it.y)
                }
            }

            drawPath(path, color = Color.Black, style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round))
        }
    }
}