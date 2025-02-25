package com.example.compose_art.composables

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_art.R
import com.example.compose_art.viewmodel.DrawingViewModel
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos

@Composable
fun DrawingCanvas() {

    val viewModel: DrawingViewModel = viewModel()
    val drawingState = viewModel.drawingState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .clipToBounds()
            .fillMaxSize()
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .background(Color.White)
                .pointerInput(true) {
                    detectDragGestures(onDragStart = {
                        viewModel.onNewPathStart()
//                    onAction(DrawingAction.OnNewPathStart)
                    }, onDragEnd = {
                        viewModel.onNewPathEnd()
//                    onAction(DrawingAction.OnNewPathEnd)
                    }, onDrag = { change, _ ->
                        viewModel.onDraw(change.position)
//                    onAction(DrawingAction.onDraw(change.position))
                    }, onDragCancel = {
                        viewModel.onNewPathEnd()
//                    onAction(DrawingAction.OnNewPathEnd)
                    })
                }

        ) {
            //all Paths
            drawingState.value.paths.fastForEach { pathData ->
                val angle = atan2(pathData.path.first().y, pathData.path.first().y)
                val width = 20f * cos(angle)
                drawPath(pathData.path, color = pathData.color, width)
            }
            //current Path
            drawingState.value.currentPath?.let {
                val angle = atan2(it.path.first().y, it.path.first().y)
                val width = 20f * cos(angle)
                drawPath(it.path, color = it.color,   width)
            }
        }

        Row(modifier =  Modifier.fillMaxWidth()
            .height(60.dp)
            .weight(1f)
            .background(Color.LightGray)
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {
            Box(modifier = Modifier.size(50.dp)
                .background(Color.Red)
                .clickable {
                viewModel.onSetColor(Color.Red)
            })

            Box(modifier = Modifier.size(50.dp)
                .background(Color.Green)
                .clickable {
                    viewModel.onSetColor(Color.Green)
                })

            Box(modifier = Modifier.size(50.dp)
                .background(Color.White)
                .clickable {
                    viewModel.onSetColor(Color.White)
                })

            Box(modifier = Modifier.size(50.dp)
                .background(Color.Black)
                .clickable {
                    viewModel.onSetColor(Color.Black)
                })

            Box(modifier = Modifier.size(50.dp)
                .background(Color.Yellow)
                .clickable {
                    viewModel.onSetColor(Color.Yellow)
                })

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

    val pathh = Path().apply {
        moveTo(path.first().x, path.first().y)
        for (i in 1..path.lastIndex) {
            lineTo(path[i].x,path[i].y)
        }

    }


   // Adjust width based on direction
    drawPath(
        path = pathh,
        color = color,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round)
    )
}
