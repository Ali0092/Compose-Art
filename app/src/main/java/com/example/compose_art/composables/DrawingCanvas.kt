package com.example.compose_art.composables

import android.annotation.SuppressLint
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.example.compose_art.viewmodel.DrawingAction
import com.example.compose_art.viewmodel.PathData
import kotlin.math.abs

@SuppressLint("UnrememberedMutableState")
@Composable
fun DotDrawing(modifier: Modifier = Modifier) {

    val offSetList = remember { mutableStateOf<List<Offset>>(emptyList()) }
    var circlePath by mutableStateOf<Offset>(Offset(100f,100f))

    var dragX by remember { mutableStateOf<Float>(100f) }
    var dragY by remember { mutableStateOf<Float>(100f) }

    var dragX2 by remember { mutableStateOf<Float>(100f) }
    var dragY2 by remember { mutableStateOf<Float>(100f) }

    Box(modifier = modifier
        .clipToBounds()
        .background(Color.White)){

        Canvas(modifier = modifier
            .clipToBounds()
            .background(Color.White)
            .pointerInput(true) {
                detectDragGestures(onDragStart = {
//                    dragX = it.x
//                    dragY = it.y

                }, onDragEnd = {

                }, onDrag = { change, dragAmount ->
//                    dragX = change.position.x
//                    dragY = change.position.y
                }, onDragCancel = {

                })
            }) {
            //here to draw the path,first to make path variable
            val path = Path().apply {
                moveTo(0f,0f)
//            lineTo(size.width,size.height)
                cubicTo(dragX,dragY, 0f, 0f, size.width,size.height)
            }

            drawPath(path, color = Color.Black, style =  Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round))

        }

        Canvas(modifier = Modifier
            .offset { IntOffset(0, 0) }
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    dragX = +dragAmount.x
                    dragY = +dragAmount.y
                }
            }){
            drawCircle(color = Color.Blue, radius = 20f)
        }

        Canvas(modifier = Modifier
            .offset { IntOffset(dragX2.toInt(), dragX2.toInt()) }
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    dragX2 = change.position.x
                    dragX2 = change.position.y
                }
            }){
            drawCircle(color = Color.Blue, radius = 20f)
        }


    }


}

@Composable
fun DrawingCanvas1(
    paths: List<PathData>,
    currentPath: PathData?,
    onAction: (DrawingAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
            .clipToBounds()
            .background(Color.White)
            .pointerInput(true) {
                detectDragGestures(onDragStart = {
                    onAction(DrawingAction.OnNewPathStart)
                }, onDragEnd = {
                    onAction(DrawingAction.OnNewPathEnd)
                }, onDrag = { change, _ ->
                    onAction(DrawingAction.onDraw(change.position))
                }, onDragCancel = {
                    onAction(DrawingAction.OnNewPathEnd)
                })
            }

    ) {
        paths.fastForEach { pathData ->
            drawPath(pathData.path, pathData.color, 5f)
        }
        currentPath?.let {
            drawPath(it.path, it.color, 5f)
        }
    }
}

fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    strokeWidth: Float) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)
            val smoothness = 5
            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to  = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)

                if (dx>=smoothness || dy>=smoothness){
                    quadraticBezierTo(
                        x1 = (from.x + to.x)/2f,
                        y1 = (from.y + to.y)/2f,
                        x2 = to.x,
                        y2 = to.y)
                }
            }

        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round)
    )
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
//                val pointsFromHistory = change.historical
//                    .map { it.position }
//                    .toTypedArray()
//                val newPoints = listOf(*pointsFromHistory, change.position)
                points = points + change.position
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

            drawPath(
                path,
                color = Color.Black,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}