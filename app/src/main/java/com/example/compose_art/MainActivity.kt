package com.example.compose_art

import android.graphics.Shader
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import com.example.compose_art.JellyFishPaths.bubble1
import com.example.compose_art.JellyFishPaths.bubble2
import com.example.compose_art.JellyFishPaths.bubble3
import com.example.compose_art.JellyFishPaths.bubble4
import com.example.compose_art.JellyFishPaths.bubble5
import com.example.compose_art.JellyFishPaths.bubble6
import com.example.compose_art.JellyFishPaths.bubble7
import com.example.compose_art.JellyFishPaths.face
import com.example.compose_art.JellyFishPaths.freckle1
import com.example.compose_art.JellyFishPaths.freckle2
import com.example.compose_art.JellyFishPaths.freckle3
import com.example.compose_art.JellyFishPaths.freckle4
import com.example.compose_art.JellyFishPaths.lefteye
import com.example.compose_art.JellyFishPaths.mouth
import com.example.compose_art.JellyFishPaths.outerLayer
import com.example.compose_art.JellyFishPaths.righteye
import com.example.compose_art.JellyFishPaths.tentaclePath1
import com.example.compose_art.JellyFishPaths.tentaclePath2
import com.example.compose_art.JellyFishPaths.tentaclePath3
import com.example.compose_art.JellyFishPaths.tentaclePath4
import com.example.compose_art.JellyFishPaths.tentaclePath5
import com.example.compose_art.JellyFishPaths.tentaclePath6
import com.example.compose_art.JellyFishPaths.tentaclePath7
import com.example.compose_art.JellyFishPaths.tentaclePath8
import com.example.compose_art.ui.theme.ComposeArtTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArtTheme {
                JellyfishAnimation()
//                InstagramLogo(modifier = Modifier.fillMaxSize())
//                MyCanvas(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
object JellyFishPaths {

    //Face
    val mouth = PathParser().parsePathString(
        "M286.84 244s-.11-.12-.32-.31a9.91 9.91 0 0 1-.81-.76l-.5-.53c-.16-.2-.31-.41-.48-.61s-.27-.46-.41-.69a6.83 6.83 0 0 1-.29-.75 5.86 5.86 0 0 1-.06-3 7.71 7.71 0 0 1 .29-1.05 3.46 3.46 0 0 1 .13-.39.49.49 0 1 0-.8-.55 3.31 3.31 0 0 0-.33.38 5.42 5.42 0 0 0-.68 1.12 5.32 5.32 0 0 0-.53 2.93 4.34 4.34 0 0 0 .07.52h-.08a9.59 9.59 0 0 1-1.64.53 11.21 11.21 0 0 1-1.15.22 10.31 10.31 0 0 1-1.29.17 12 12 0 0 1-1.41.1c-.48 0-1.07.05-1.48 0l-1.46-.06c-.48 0-.94-.11-1.39-.15s-.88-.17-1.29-.23-.78-.2-1.13-.29-.65-.22-.93-.31a5 5 0 0 1-.67-.28 2.56 2.56 0 0 0 0-.27 5.08 5.08 0 0 0 0-1.1 6 6 0 0 0-1.12-3 2.65 2.65 0 0 0-.32-.39.5.5 0 0 0-.67-.15.49.49 0 0 0-.15.68 3.33 3.33 0 0 1 .12.39 9.13 9.13 0 0 1 .26 1.06 5.81 5.81 0 0 1-.14 3 6.81 6.81 0 0 1-.31.74l-.43.67c-.18.2-.34.41-.49.61l-.52.51a9 9 0 0 1-.83.74c-.21.18-.33.3-.33.3a.49.49 0 0 0 .5.81s.16-.05.44-.17a6.14 6.14 0 0 0 1.15-.56c.22-.15.48-.3.72-.49s.49-.43.73-.68.45-.57.67-.87.24-.49.35-.74a2.77 2.77 0 0 0 .42.27 7 7 0 0 0 1 .53 7.33 7.33 0 0 0 1.23.48 8.56 8.56 0 0 0 1.41.38c.5.08 1 .2 1.54.24l1.6.09c.61 0 1.05 0 1.58-.07a11.79 11.79 0 0 0 1.53-.18 9.58 9.58 0 0 0 1.44-.31 8.32 8.32 0 0 0 1.25-.42 6.92 6.92 0 0 0 1-.47 5.28 5.28 0 0 0 .6-.36c.09.19.18.37.26.55s.42.62.64.89.48.49.71.71.49.35.71.51a7.4 7.4 0 0 0 1.13.59l.44.18a.47.47 0 0 0 .51-.11.48.48 0 0 0 .01-.6z"
    ).toNodes()

    val face = PathParser().parsePathString(
        "M283.26 178.86c-17.72-1.84-36.33 5.33-52.24 17.37a110.1 110.1 0 0 0-22.14 22.06c-8.54 11.67-14 31.93-7.48 47.29 8.82 20.73 29.67 14.17 44.32 15.6 14.34 1.55 28.68-.52 43-.52 10.47 0 20.94 1 31.4-.51 9.36-1.39 21.38-9.12 24-22.07 4.24-21-13.77-45.52-24.27-58.37-10.69-13.1-23.39-19.48-36.59-20.85z"
    ).toNodes()
    val lefteye = PathParser().parsePathString("M262 233.63a3.1 3.1 0 1 0-3 3.19 3.1 3.1 0 0 0 3-3.19z").toNodes()
    val righteye = PathParser().parsePathString("M294 232.72a3.1 3.1 0 1 0-3 3.18 3.1 3.1 0 0 0 3-3.18z").toNodes()

    //Tantacles...
    val tentaclePath1 = PathParser().parsePathString(
        "M276.84 253.3c7.24 20.7 13 43.91-2.15 63.75-9.11 11.91-35.1 42.78-31.45 57.53 3.22 13.05 13.8 19.9 11.68 23.22-2.87 3.86-16.15-16.76-16.55-25.86-.72-16.2 12.73-33.92 29.47-53.28 2-2.36 10.84-14.91 11.57-29.33.82-16.41-10.59-35.12-10.2-35.12 2.54-.02 6.64-3.74 7.63-.91z"
    ).toNodes()
    val tentaclePath2 = PathParser().parsePathString(
        "M246.47 259.51a75 75 0 0 1 3.1 33.39c-1.55 11.71-8.91 20.82-8.91 33 0 10 3.48 18.21 5.81 27.75 2.71 10.41.39 19.51-.78 30.35-1.55 9.11-4.65 24.29-.77 33.39s7.37 4.77 7.37-2.6c0-6.07-.78-11.71-1.17-17.77-.39-8.25 3.1-12.58 5-20.39 5.43-21.24-9.3-42.06-3.49-63.31 2.72-11.27 10.86-22.55 11.25-34.26.38-6.07-1.55-14.74-2.72-20.38z"
    ).toNodes()
    val tentaclePath3 = PathParser().parsePathString(
        "M276.54 262.11c-1.55 9.11 2.21 17.35-.51 26.89-3.87 12.57-8.2 23.41-8.58 37.29 0 10.41 5.78 15.61 7.72 25.15 2.72 11.28-2.73 19.95-5.44 30.35-1.94 6.51-3.11 15.62-.79 22.12 3.49 8.24 5.43 7.81 3.88 18.21-.78 4.77-2.72 17.35 4.26 19.09 5.43.86 5.82-6.94 5.82-11.72-.39-12.57-3.49-24.28-3.11-37.29.39-14.31 4.66-27.75 5.82-42.06.39-7.37-1.94-13-3.49-19.95-2.33-10.4-.78-15.17 3.1-24.71 4.65-10.85 5.28-21.25 6.06-33 .77-6.07 2.53-13-.54-18.65v.44z"
    ).toNodes()
    val tentaclePath4 = PathParser().parsePathString(
        "M300.34 261.25c1.55 14.3 7 19.94 1.17 34.68-5.43 12.15-8.53 22.12-3.88 35.13 3.88 10.84 5.43 18.21 3.88 30.36-1.56 10-2.33 19.94 4.26 28.61 13.57 17.35 10.86-10.4 8.14-19.07-1.55-6.08-3.49-11.71-2.71-18.22 1.16-8.24 3.88-14.31 1.55-23-1.55-6.07-5-10.41-3.88-17.35s5.82-12.14 6.59-19.51c1.17-7.37 3.49-30.35-3.1-35.56z"
    ).toNodes()
    val tentaclePath5 = PathParser().parsePathString(
        "M254.44 253.48c-8.78 25.12-15.81 53.28 2.61 77.35 11.06 14.46 42.58 51.91 38.16 69.8-3.91 15.83-20.78 24.73-14.17 28.18 8.22 3.21 22.2-26 18.7-41.25-3.89-16.94-17.68-35.47-34.38-54.78-2.48-2.86-13.15-18.09-14-35.58-1-19.92 12.84-42.61 12.38-42.62-3.13-.02-8.1-4.53-9.3-1.1z"
    ).toNodes()
    val tentaclePath6 = PathParser().parsePathString(
        "M295.27 254.27c5.83 16.69 12.69 18.82 1.58 35.22a45 45 0 0 0-6.67 19.22c-.52 6.52 6.09 13.33 5.5 19.6-2.35 24.92-17.13 46.55-19.33 44.29-4.35-4.6 22.07-28.6 12.46-51.16-8.38-19.64 6.49-36.53 9.9-42.49 2.75-4.8-11.51-24.51-11.42-24.51 2.05-.01 5.93-.6 7.98-.17z"
    ).toNodes()
    val tentaclePath7 = PathParser().parsePathString(
        "M256.56 252.22c-7 6.09-17.21 14-23.17 24-5.55 9.25-7.06 20.43-7.66 27.88-.53 6.52 2.21 13.76 5.49 19.6 14 24.92-15.62 46.55-19.33 44.29-4.55-4 14.59-16.95 17.41-23.45 2.91-6.69-.07-16.29-5-27.71-8.38-19.64 1-39.25 4.37-45.2 2.75-4.81 19.82-19 19.91-19 2.1-.07 5.92-.84 7.98-.41z"
    ).toNodes()
    val tentaclePath8 = PathParser().parsePathString(
        "M288.76 254.4c7 6.09 17.21 14 23.17 24 5.55 9.25 7.06 20.43 7.66 27.88.53 6.52-2.21 13.76-5.49 19.6-14 24.93 9 45 19.33 44.29 6.58-4.07-14.59-16.95-17.41-23.45-2.91-6.69.07-16.28 5-27.71 8.38-19.64-1-39.25-4.37-45.2-2.75-4.81-19.82-19-19.91-19-2.1-.06-5.92-.81-7.98-.41z"
    ).toNodes()


    //OuterLayer (0.5 opacity)
    val outerLayer = PathParser().parsePathString(
        "M340.52 295.71c-10.12 1.19-15.63-.12-20.38-1.64-3.63-1.17-6.84-2.44-11.37-2.83l-1-.06c-1-.06-2.05-.06-3.17 0a21.37 21.37 0 0 0-8 2.28c-1.27.59-2.6 1.21-4.09 1.82a42.23 42.23 0 0 1-10.6 3c-1 .16-2 .29-3.11.37-1.56.14-3.26.22-5.09.22h-.51c-1.74 0-3.35-.08-4.84-.2-.6 0-1.15-.08-1.7-.15a47.37 47.37 0 0 1-9.43-1.8 8.25 8.25 0 0 1-.92-.29 75 75 0 0 1-8-3.34L248 293a19.06 19.06 0 0 0-6.86-1.81c-1.27-.06-2.44-.12-3.56-.14h-1a51.6 51.6 0 0 0-9.87.67c-8 1.38-10.86 4.29-22.82 4.06-22.13 0-9.51-11.13-11-17.8-7.24-33.11-.69-63.69 18-83.91 14.65-15.86 35.74-24.25 61-24.25 23.56 0 43.94 8.81 59 25.44 19.21 21.29 27 53.84 20 83-1.67 6.46 10.68 14.95-10.37 17.45z"
    ).toNodes()

    //Freckles
    val freckle1 = PathParser().parsePathString(
        "M330 225.23a4.16 4.16 0 0 0-.1.55c-.52 3.79 3.9 6.73 5.91 3 2.42-4.46-4.66-8.14-5.81-3.55z"
    ).toNodes()
    val freckle2 = PathParser().parsePathString(
        "M322.89 215.5a3.94 3.94 0 0 0-1.32 3.61 3 3 0 1 0 5.91 0c.27-3.81-2.76-4.98-4.59-3.61z"
    ).toNodes()
    val freckle3 = PathParser().parsePathString(
        "M206.88 241.24c-.88 2-.11 4.56 3.36 4.27 3.87-.33 5.42-5.85.75-6.62a3.79 3.79 0 0 0-4.11 2.35z"
    ).toNodes()
    val freckle4 = PathParser().parsePathString(
        "M210.11 225.75c.83-.07 1.7.52 1.92 2.17.4 3-2.5 3.09-3.4.6a2 2 0 0 1 1.48-2.77z"
    ).toNodes()


    //Bubble..
    val bubble1 = PathParser().parsePathString(
        "M384.85 167.41c-7.37 4.94-6.92 18 4.34 16.82a19.89 19.89 0 0 0 3.34-.71c13.54-4.17 9.8-20.35-3.66-17.77a10.52 10.52 0 0 0-4.02 1.66z"
    ).toNodes()
    val bubble2 = PathParser().parsePathString(
        "M130.5 266.76c-6 4-5.59 14.59 3.51 13.61a15.22 15.22 0 0 0 2.7-.57c11-3.38 7.93-16.47-3-14.38a8.85 8.85 0 0 0-3.21 1.34z"
    ).toNodes()
    val bubble3 = PathParser().parsePathString(
        "M424.53 152.72c2.41-6-5.11-13.82-12-7.41s2.41 15.31 9.3 10.64a7.19 7.19 0 0 0 2.7-3.23z"
    ).toNodes()
    val bubble4 = PathParser().parsePathString(
        "M392.89 142.46c1.65-4.09-3.5-9.45-8.22-5.07s1.65 10.47 6.36 7.28a4.87 4.87 0 0 0 1.86-2.21z"
    ).toNodes()
    val bubble5 = PathParser().parsePathString(
        "M114 242.88c-2-.26-4.34 1.09-3.68 4.42 1.68 8.05 11.35-3.48 3.68-4.42z"
    ).toNodes()
    val bubble6 = PathParser().parsePathString(
        "M110.78 259.61c3.62-2.73 4.8-9.7-.42-12.31-7.07-3.53-12.93 6.88-6.75 11.86 2.68 2.16 5.26 1.89 7.17.45z"
    ).toNodes()
    val bubble7 = PathParser().parsePathString(
        "M141.82 214.42c-2.7.07-5.63 2.5-4.54 5.75 1.68 4.62 9.19 2.19 8.37-2.49a3.6 3.6 0 0 0-3.83-3.26z"
    ).toNodes()


}

// create a custom gradient background that has a radius that is the size of the biggest dimension of the drawing area, this creates a better looking radial gradient in this case.
val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}

@Composable
fun JellyfishAnimation() {

    val vectorPainter = rememberVectorPainter(
        defaultWidth = 530.46f.dp,
        defaultHeight = 563.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 563.1f,
        autoMirror = true,
    ) { _, _ ->

        val duration = 3000
        val transition = rememberInfiniteTransition(label = "")
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = -30f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val translationY2 by transition.animateFloat(
            initialValue = -30f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Group(name = "jellyfish", translationY = translationY) {
            Group(name = "body") {
                // inner and outer jelly paths
                Path(
                    pathData = outerLayer,
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.4f
                )
            }
            Group(name = "freckles") {
                // freckle paths
                Path(
                    pathData = freckle1,
                    fill = SolidColor(Color.Black),
                )
                Path(
                    pathData = freckle2,
                    fill = SolidColor(Color.Black),
                )
                Path(
                    pathData = freckle3,
                    fill = SolidColor(Color.Black),
                    fillAlpha = 0.4f
                )
                Path(
                    pathData = freckle4,
                    fill = SolidColor(Color.Black),
                    fillAlpha = 0.4f
                )

            }
            Group(name = "face") {
                Path(
                    pathData = face,
                    fill = SolidColor(Color.White)
                )
                Path(
                    pathData = lefteye,
                    fill = SolidColor(Color.Black),
                    fillAlpha = 0.49f
                )
                Path(
                    pathData = righteye,
                    fill = SolidColor(Color.Black),
                    fillAlpha = 0.49f
                )
                Path(
                    pathData = mouth,
                    fill = SolidColor(Color.Black),
                    fillAlpha = 0.49f
                )
            }
            Group(name = "tentacles") {

                Path(
                    pathData = tentaclePath1,
                    fill = SolidColor(Color.White),
//                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath2,
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath3,
                    fill = SolidColor(Color.White),
//                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath4,
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath5,
                    fill = SolidColor(Color.White),
//                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath6,
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath7,
                    fill = SolidColor(Color.White),
//                    fillAlpha = 0.49f
                )
                Path(
                    pathData = tentaclePath8,
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.49f
                )
            }
        }

        Group(name = "bubbles", translationY = translationY2) {
            // bubbles around the jellyfish
            Path(
                pathData = bubble1,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble2,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble3,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble4,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble5,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble6,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
            Path(
                pathData = bubble7,
                fill = SolidColor(Color.White),
                fillAlpha = 0.60f
            )
        }

    }

    Image(
        vectorPainter,
        contentDescription = "Jellyfish",
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    )
}
@Composable
fun MyCanvas(modifier: Modifier) {
    Canvas(modifier = modifier.background(color = Color.White)) {

    }
}

@Composable
fun InstagramLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.background(color = Color.White)) {

        val rectWidth = 150f  // Rectangle width
        val rectHeight = 150f // Rectangle height

        val left = (size.width - rectWidth) / 2
        val top = (size.height - rectHeight) / 2
        val left2 = size.width / 2 + 40
        val top2 = size.height / 2 - 40

        // Define gradient colors (similar to Instagram's logo)
        val gradientColors = listOf(
            Color.Red, Color.Blue
        )

        // Create a linear gradient shader
        val gradientBrush = Brush.horizontalGradient(
            colors = gradientColors, tileMode = TileMode.Clamp
        )

        //outer circle
        drawCircle(brush = gradientBrush, radius = 150f)
        //inner most circle
        drawCircle(
            color = Color.White, radius = 25f, style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
        //side dot circle
        drawCircle(color = Color.White, radius = 10f, style = Fill, center = Offset(left2, top2))
        //main rect
        drawRoundRect(
            color = Color.White,
            size = Size(rectWidth, rectHeight),
            topLeft = Offset(left, top),
            style = Stroke(width = 10f, cap = StrokeCap.Round),
            cornerRadius = CornerRadius(15f, 15f)
        )

    }
}
