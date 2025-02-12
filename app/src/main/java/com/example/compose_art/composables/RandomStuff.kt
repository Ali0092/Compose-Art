package com.example.compose_art.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun RandomPatterns(modifier: Modifier = Modifier) {
    Canvas(modifier.background(color = Color.White)) {


        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = calculateOffSet(size,200f, 200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawOval(
            color = Color.Red,
            size = Size(350f,400f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round),
            topLeft = calculateOffSet(size,350f, 400f),
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset(0f,0f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset(size.width-200f,0f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset(0f,size.height-200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset(size.width-200f,size.height-200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset(size.width-200f,size.height-200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset((size.width/2)-200f,(size.height/2)-200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset((size.width/2)-200f,(size.height/2)),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset((size.width/2),(size.height/2)-200f),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

        drawRoundRect(
            color = Color.Black,
            size = Size(200f,200f),
            cornerRadius = CornerRadius(12.dp.toPx(),12.dp.toPx()),
            topLeft = Offset((size.width/2),(size.height/2)),
            style = Stroke(width = 5.dp.toPx(),
                cap = StrokeCap.Round)
        )

    }
}

fun calculateOffSet(size: Size, rectAngleWidth: Float, rectAngleHeight: Float ): Offset {
    return Offset((size.width/2)-(rectAngleWidth/2f),(size.height/2)-(rectAngleHeight/2f))
}

@Composable
fun CirclePatters(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier
        .background(color = Color.White)
        .drawBehind {

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(0f, 0f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width, 0f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(0f, size.height),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width, size.height),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, size.height / 2),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, 0f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, size.height),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(0f, size.height / 2),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width, size.height / 2),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(0f, size.height / 4),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(0f, size.height / 1.33f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width, size.height / 4),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width, size.height / 1.33f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, size.height / 4),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, size.height / 1.33f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 2, size.height / 1.33f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            //Dignols
            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 4, size.height / 8),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 4, size.height / 2.66f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 4, size.height / 1.6f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 4, size.height / 1.14f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )


            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 1.33f, size.height / 8),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 1.33f, size.height / 2.66f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 1.33f, size.height / 1.6f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = Color.Black,
                radius = 100.dp.toPx(),
                center = Offset(size.width / 1.33f, size.height / 1.14f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )

        })
}
