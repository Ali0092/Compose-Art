package com.example.compose_art.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

object BeachScenePaths {
    val treePart =
        PathParser().parsePathString(pathData = "M320,768c277.3,-128 256,-554.7 256,-554.7h42.7s42.7,469.4 -189.7,635.4L320,768z")
            .toNodes()
    val leaf2Path =
        PathParser().parsePathString(pathData = "M256,277.3c-0.5,-38.6 15,-77.3 40,-107.8 25,-30.3 63.1,-53.8 105.4,-60.6 42.2,-7.1 84.8,2.7 118.4,21.6 33.7,19.2 60.6,46.1 77.6,80.4 -37.8,-5.5 -70.1,-13.3 -100.3,-17.3 -30.1,-4.2 -56.9,-4.6 -81.8,-0.5s-49.5,12.8 -75.6,27C313.5,234.4 286.8,255.3 256,277.3zM874.7,277.3l-74.4,-37.8c-21.9,-10.8 -41.6,-18.8 -60.5,-23.9 -18.9,-5.1 -37.5,-7.9 -60.6,-7.9C656.1,207.6 629.9,211.2 597.3,213.3c11.3,-30.6 33.6,-54.6 63.3,-70 29.5,-15.3 67.8,-19.7 101.7,-9.9 34.2,9.4 62.5,30.6 81.8,55.7 19.1,25.5 31.4,55.2 30.5,88.1z")
            .toNodes()
    val leaf1Path =
        PathParser().parsePathString(pathData = "M597.3,213.3c-40,21.9 -69.6,41.1 -93.1,60.6 -23.4,19.5 -39.4,38.2 -55.1,59.7 -15.6,21.5 -30.8,46.9 -47.3,76.9C384.9,440.4 368.1,474.3 341.3,512c-26.6,-37.9 -37.5,-79.9 -37.6,-123.8 0.3,-43.5 12.9,-90.2 42,-130 28.5,-40 74.6,-69.6 121.7,-77.5C514.5,172.8 562.3,182.3 597.3,213.3zM810.7,533.3c-24.8,-36 -41.7,-66.4 -57.6,-94.9a3325.9,3325.9 0,0 0,-42.8 -76.9c-13.7,-23.3 -26.5,-44.6 -43.8,-67.8C649.5,270.1 627.2,246 597.3,213.3c39.4,-20 84.7,-19.2 125.9,-2.8 41.2,16.4 76.4,49 97.8,86.8a228.7,228.7 0,0 1,30.2 120.6c-1.8,40.6 -12.4,80.9 -40.6,115.3z")
            .toNodes()
    val waterPath =
        PathParser().parsePathString(pathData = "M1130,789.4s-25,42.7 -100,42.7 -100,-42.7 -100,-42.7 -25,42.7 -100,42.7 -100,-42.7 -100,-42.7 -25,42.7 -100,42.7 -100,-42.7 -100,-42.7 -25,42.7 -100,42.7 -100,-42.7 -100,-42.7 -25,42.7 -100,42.7v95.4h1000v-150z")
            .toNodes()
    val beachPath =
        PathParser().parsePathString(pathData = "M10.7,917.3l576,0s-192,-170.7 -576,-192V917.3z")
            .toNodes()
    val leaf3Path =
        PathParser().parsePathString(pathData = "M597.3,213.3c-85.3,106.7 -106.7,341.3 -106.7,341.3M597.3,213.3c2.5,39.8 3.4,68.7 1.5,97.9a418.1,418.1 0,0 1,-13.6 82.9,368.6 368.6,0 0,1 -32.2,80.3C538.2,501.1 520.2,527.6 490.7,554.7c-23.5,-32.9 -32.4,-66 -36.5,-98.5a255.1,255.1 0,0 1,7.7 -95.9,244.3 244.3,0 0,1 44.7,-88.1c21.8,-26.2 50.9,-51.2 90.8,-58.7z")
            .toNodes()
}

@Composable
fun BeachScene() {

    var isNight by remember { mutableStateOf(false) }

    val sunColor by animateColorAsState(
        targetValue = if(isNight) Color.White else Color.Yellow,
        animationSpec = tween(durationMillis = 1500), // 1-second fade duration
        label = "sunColor"
    )

    val dayNightColors by animateColorAsState(
        targetValue =  if(isNight) Color(0xFF0D1B2A) else Color(0xFF87CEEB),
        animationSpec = tween(durationMillis = 1000), // 1-second fade duration
        label = "dayNightColors"
    )

    val overLayCircleSize by animateFloatAsState(
        targetValue = if(isNight) 120f else 0f,
        animationSpec = tween(1000),
        label = "overLayCircleSize"
    )

    val beachVectorPainter = rememberVectorPainter(
        defaultWidth = 900.dp,
        defaultHeight = 900.dp,
        viewportWidth = 1024f,
        viewportHeight = 1024f,
        autoMirror = true,
    ) { _, _ ->
        //Vector Composable
        Group(name = "Beach_Scene") {
            Path(pathData = BeachScenePaths.treePart, fill = SolidColor(Color(0xff795548)))
            Path(pathData = BeachScenePaths.leaf1Path, fill = SolidColor(Color(0xff57C927)))
            Path(pathData = BeachScenePaths.leaf2Path, fill = SolidColor(Color(0xff1CB71C)))
            Path(pathData = BeachScenePaths.leaf3Path, fill = SolidColor(Color(0xff8BC34A)))
            Path(pathData = BeachScenePaths.waterPath, fill = SolidColor(Color(0xff2196F3)))
            Path(pathData = BeachScenePaths.beachPath, fill = SolidColor(Color(0xffFFC107)))
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .background( dayNightColors )
        .clickable { isNight = !isNight },
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Moon and Sun part
        Canvas(modifier = Modifier.weight(1f)) {
            val height = size.height
            val width = size.width
            drawCircle(
                radius = 120f,
                color = sunColor
            )
            drawCircle(
                center = Offset((width / 2) - 40f, (height / 2) - 40f),
                radius = overLayCircleSize,
                color =  dayNightColors
            )

        }
        //Drawing Beach Vector
        Image(
            painter = beachVectorPainter,
            contentDescription = "beach_vector",
            modifier = Modifier.weight(1f).offset( x = -8.dp, y=70.dp)
        )
    }

}