package com.example.compose_art

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawingCanvas()
        }
    }
}

@Composable
fun DrawingCanvas() {

    val path = remember { Path() }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .pointerInput(true) {
            detectDragGestures(onDrag = { pointerInputChange, dragAmount ->
                path.quadraticBezierTo(
                    pointerInputChange.position.x,
                    pointerInputChange.position.y,
                    (pointerInputChange.position.x + pointerInputChange.position.x) / 2,
                    (pointerInputChange.position.y + pointerInputChange.position.y) / 2

                )
            }, onDragStart = { offset ->
                path.moveTo(offset.x, offset.y)
            })
        }) {
        drawPath(path, color = Color.Black, style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}