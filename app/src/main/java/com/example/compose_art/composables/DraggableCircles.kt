package com.example.compose_art.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun DraggableCircles() {

    var blueOffset by remember { mutableStateOf(Offset(100f, 100f)) }
    var redOffset by remember { mutableStateOf(Offset(300f, 300f)) }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

        Canvas(modifier = Modifier
            .clipToBounds().fillMaxSize()) {
            //here to draw the path,first to make path variable
            val path = Path().apply {
                moveTo(0f,0f)
                cubicTo(blueOffset.x,blueOffset.y, redOffset.x, redOffset.y, size.width,size.height)
            }

            drawPath(path, color = Color.Black, style =  Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round))
            drawLine(color = Color.Gray, start = Offset(0f,0f), end = Offset(blueOffset.x+20f, blueOffset.y+20f), strokeWidth = 4f)
            drawLine(color = Color.Gray, start = Offset(size.width,size.height), end = Offset(redOffset.x+20f, redOffset.y+20f), strokeWidth = 4f)

        }
        // Blue Circle
        Canvas(
            modifier = Modifier
                .offset { IntOffset(blueOffset.x.toInt(), blueOffset.y.toInt()) }
                .size(25.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        blueOffset += dragAmount
                    }
                }
        ) {
            drawCircle(color = Color.Blue, radius = 25.dp.toPx())
        }

        // Red Circle
        Canvas(
            modifier = Modifier
                .offset { IntOffset(redOffset.x.toInt(), redOffset.y.toInt()) }
                .size(25.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        redOffset += dragAmount
                    }
                }
        ) {
            drawCircle(color = Color.Red, radius = 25.dp.toPx())
        }
    }
}
