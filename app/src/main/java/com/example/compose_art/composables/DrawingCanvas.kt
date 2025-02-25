package com.example.compose_art.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_art.viewmodel.DrawingViewModel
import kotlin.math.abs

@Composable
fun DrawingCanvas() {

    val viewModel: DrawingViewModel = viewModel()
    val drawingState = viewModel.drawingState.collectAsStateWithLifecycle()
    var showColorRow by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .clipToBounds()
            .fillMaxSize()
    ) {

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

        //Top Row....
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9AD5FF))
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
                    .clickable {
                        showColorRow = !showColorRow
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Colors", textAlign = TextAlign.Center, fontSize = 16.sp)
                }

                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                        .clickable {
                            viewModel.onSetColor(Color.White)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Eraser", textAlign = TextAlign.Center, fontSize = 16.sp)
                }
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                        .clickable {
                            viewModel.onClearedCanvasClicked()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Clear", textAlign = TextAlign.Center, fontSize = 16.sp)
                }

            }
            if (showColorRow) {
                LazyRow(modifier = Modifier.padding(top = 10.dp), state = rememberLazyListState()) {
                    items(colorsList) { color ->
                        Card(
                            modifier = Modifier
                                .size(50.dp)
                                .background(color)
                                .clickable {
                                    viewModel.onSetColor(color)
                                },
                            shape = RoundedCornerShape(50.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = color
                            )
                        ) {}
                    }
                }
            }
        }

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(6f)
            .background(Color.White)
            .pointerInput(true) {
                detectDragGestures(onDragStart = {
                    viewModel.onNewPathStart()
                }, onDragEnd = {
                    viewModel.onNewPathEnd()
                }, onDrag = { change, _ ->
                    viewModel.onDraw(change.position)
                }, onDragCancel = {
                    viewModel.onNewPathEnd()
                })
            }

        ) {
            //all Paths
            drawingState.value.paths.fastForEach { pathData ->
                drawPath(pathData.path, color = pathData.color, strokeWidth = 10f)
            }
            //current Path
            drawingState.value.currentPath?.let {

                drawPath(it.path, color = it.color, strokeWidth = 10f)
            }
        }
    }

}

fun DrawScope.drawPath(
    path: List<Offset>, color: Color, strokeWidth: Float
) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)
            val smoothness = 5

            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)

                if (dx >= smoothness || dy >= smoothness) {
                    quadraticBezierTo(
                        x1 = (from.x + to.x) / 2f, y1 = (from.y + to.y) / 2f, x2 = to.x, y2 = to.y
                    )
                }
            }

        }
    }

    // Adjust width based on direction
    drawPath(
        path = smoothedPath, color = color, style = Stroke(
            width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round
        )
    )
}