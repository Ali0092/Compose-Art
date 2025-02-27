package com.example.compose_art.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_art.utils.Utils.brushList
import com.example.compose_art.utils.Utils.colorsList
import com.example.compose_art.viewmodel.DrawingViewModel
import kotlin.math.abs

@Composable
fun DrawingCanvas() {

    val viewModel: DrawingViewModel = viewModel()
    val drawingState = viewModel.drawingState.collectAsStateWithLifecycle()
    var showColorRow by remember { mutableStateOf(false) }
    var showBrushes by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Top Bar
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
                //brushes
                TopRowItem(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f), "Brushes"
                ) {
                    showBrushes = !showBrushes
                }
                //Colors
                TopRowItem(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f), "Colors"
                ) {
                    showColorRow = !showColorRow
                }
                //Eraser
                TopRowItem(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f), "Eraser"
                ) {
                    viewModel.onSetColor(Color.White)
                    viewModel.onSetBrush(null)
                }
                //Clear Canvas
                TopRowItem(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f), "Clear"
                ) {
                    viewModel.onClearedCanvasClicked()
                }
            }
            //brushes row
            if (showBrushes) {
                BrushesRow(context, viewModel = viewModel)
            }
            //color selection row
            if (showColorRow) {
                ColorsRow(viewModel)
            }

        }

        //Canvas
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(6f)
            .clipToBounds()
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
                drawPath(
                    path = pathData.path,
                    image = pathData.brush,
                    color = pathData.color,
                    strokeWidth = 50f
                )
            }
            //current Path
            drawingState.value.currentPath?.let {
                drawPath(
                    path = it.path, image = it.brush, color = it.color, strokeWidth = 50f
                )
            }
        }
    }
}

fun DrawScope.drawPath(
    path: List<Offset>, image: ImageBitmap? = null, color: Color, strokeWidth: Float
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

    if (image != null) {
        path.forEach { offSet ->
            drawImage(image, topLeft = offSet, colorFilter = ColorFilter.tint(color))
        }
    } else {
        drawPath(
            path = smoothedPath, color = color, style = Stroke(
                width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round
            )
        )
    }
}

fun getBitmapOfImage(context: Context, resId: Int?): ImageBitmap? {
    if (resId == null) {
        return null
    }
    val original = BitmapFactory.decodeResource(context.resources, resId)
    return Bitmap.createScaledBitmap(original, 100, 100, true).asImageBitmap()
}

@Composable
fun TopRowItem(modifier: Modifier = Modifier, text: String = "", onItemClicked: () -> Unit) {
    Row(
        modifier = modifier.clickable {
            onItemClicked()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = text, textAlign = TextAlign.Center, fontSize = 16.sp)
    }

}

@Composable
fun BrushesRow(context: Context, viewModel: DrawingViewModel) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp),
        state = rememberLazyListState()) {
        items(brushList) { img ->
            Card(modifier = Modifier
                .size(50.dp)
                .padding(3.dp)
                .clickable {
                    viewModel.onSetBrush(
                        getBitmapOfImage(
                            context, img.brushResource
                        )
                    )
                }) {
                Image(
                    painter = painterResource(img.brushPlaceHolder),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun ColorsRow(viewModel: DrawingViewModel) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp),
        state = rememberLazyListState()) {
        items(colorsList) { color ->
            Card(
                modifier = Modifier
                    .size(35.dp)
                    .background(color)
                    .clickable {
                        viewModel.onSetColor(color)
                    },
                colors = CardDefaults.cardColors(containerColor = color)
            ) {}
        }
    }
}