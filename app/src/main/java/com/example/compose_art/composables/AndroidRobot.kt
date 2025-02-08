package com.example.compose_art.composables

import android.graphics.Shader
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.example.compose_art.composables.AndroidRobot.body


val dayGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF87CEEB), Color(0xFFADD8E6), Color(0xFFFFFFFF)),
            center = size.center,
            radius = biggerDimension / 2f,
        )
    }
}

object AndroidRobot {
    val body =
        PathParser().parsePathString(pathData = "M115.43,155.43v217.66c0,17 10.21,30.34 27.7,30.34h22.84c-0.78,0 -2.54,5.77 -2.54,8.6v61.65c0,16.11 15.45,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18v-61.65c0,-2.83 -3.09,-8.6 -3.85,-8.6h55.71c-0.76,0 -3.86,5.77 -3.86,8.6v61.65c0,16.11 15.42,29.18 31.97,29.18c16.59,0 32.03,-13.06 32.03,-29.18v-61.65c0,-2.83 -1.75,-8.6 -2.54,-8.6h22.87c17.5,0 27.66,-13.34 27.66,-30.34V155.43H113.6H115.43z")
            .toNodes()
    val leftArm =
        PathParser().parsePathString(pathData = "M59.43,158.98c-16.57,0 -32,13.07 -32,29.18v124.92c0,16.11 15.43,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18V188.16C91.43,172.05 75.99,158.98 59.43,158.98z")
            .toNodes()
    val head =
        PathParser().parsePathString(pathData = "M320.3,42.06l5.58,-8.19l5.59,-8.1l12.46,-18.2c1.56,-2.26 0.91,-5.26 -1.38,-6.74c-2.27,-1.51 -5.42,-0.88 -6.9,1.36l-19.02,27.7l-5.72,8.34c-18.07,-6.83 -38.21,-10.64 -59.48,-10.64c-21.22,0 -41.4,3.82 -59.47,10.64l-5.69,-8.34l-5.62,-8.18l-13.36,-19.51c-1.54,-2.25 -4.65,-2.84 -6.95,-1.36c-2.28,1.49 -2.91,4.5 -1.39,6.74l12.45,18.21l5.59,8.1l5.62,8.17c-42.43,19.24 -71.14,57.37 -71.14,97.37h279.96C391.41,99.43 362.71,61.31 320.3,42.06zM191.44,100.59c-8.31,0 -15.01,-6.54 -15.01,-14.61s6.7,-14.58 15.01,-14.58c8.29,0 15,6.5 15,14.58S199.73,100.59 191.44,100.59zM311.44,100.59c-8.3,0 -15.02,-6.54 -15.02,-14.61s6.71,-14.58 15.02,-14.58c8.29,0 15,6.5 15,14.58S319.72,100.59 311.44,100.59z")
            .toNodes()
    val leftArmCover =
        PathParser().parsePathString(pathData = "M60.85,224.19c-12.47,0 -25.42,-11.77 -33.42,-30.43v119.32c0,16.11 15.43,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18V199.99C83.43,214.98 71.86,224.19 60.85,224.19z")
            .toNodes()
    val rightArm =
        PathParser().parsePathString(pathData = "M443.43,158.98c-16.57,0 -32,13.07 -32,29.18v124.92c0,16.11 15.43,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18V188.16C475.43,172.05 459.99,158.98 443.43,158.98z")
            .toNodes()
    val rightArmCover =
        PathParser().parsePathString(pathData = "M444.85,224.19c-12.47,0 -25.42,-11.77 -33.42,-30.43v119.32c0,16.11 15.43,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18V199.99C467.43,214.98 455.86,224.19 444.85,224.19z")
            .toNodes()
    val dress =
        PathParser().parsePathString(pathData = "M251.43,179.34c-63.28,0 -120,-7.32 -136,-17.71v211.47c0,17 10.21,30.34 27.7,30.34h22.84c-0.78,0 -2.54,5.77 -2.54,8.6v61.65c0,16.11 15.45,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18v-61.65c0,-2.83 -3.09,-8.6 -3.85,-8.6h55.71c-0.76,0 -3.86,5.77 -3.86,8.6v61.65c0,16.11 15.42,29.18 31.97,29.18c16.59,0 32.03,-13.06 32.03,-29.18v-61.65c0,-2.83 -1.75,-8.6 -2.54,-8.6h22.87c17.5,0 27.66,-13.34 27.66,-30.34v-211.48C371.43,172.01 314.72,179.34 251.43,179.34z")
            .toNodes()
    val headColor =
        PathParser().parsePathString(pathData = "M326.44,85.98c0,8.07 -6.71,14.61 -15,14.61c-8.3,0 -15.02,-6.54 -15.02,-14.61c0,-4.38 2.01,-8.24 5.14,-10.91c-15.82,-2.64 -32.64,-4.09 -50.13,-4.09s-34.3,1.45 -50.13,4.09c3.14,2.66 5.14,6.54 5.14,10.91c0,8.07 -6.71,14.61 -15,14.61c-8.31,0 -15.01,-6.54 -15.01,-14.61c0,-2.06 0.46,-4.02 1.25,-5.81c-23.98,6.3 -44.59,15.5 -60.14,26.81c-3.92,10.3 -6.09,24.46 -6.09,32.46h279.96c0,-8 -2.17,-22.15 -6.08,-32.44c-15.54,-11.32 -36.16,-20.54 -60.13,-26.84C325.99,81.94 326.44,83.92 326.44,85.98z")
            .toNodes()
    val bodyDress =
        PathParser().parsePathString(pathData = "M251.43,262.82c-53.9,0 -104,-10.63 -136,-28.06v138.34c0,17 10.21,30.34 27.7,30.34h22.84c-0.78,0 -2.54,5.77 -2.54,8.6v61.65c0,16.11 15.45,29.18 32,29.18c16.56,0 32,-13.06 32,-29.18v-61.65c0,-2.83 -3.09,-8.6 -3.85,-8.6h55.71c-0.76,0 -3.86,5.77 -3.86,8.6v61.65c0,16.11 15.42,29.18 31.97,29.18c16.59,0 32.03,-13.06 32.03,-29.18v-61.65c0,-2.83 -1.75,-8.6 -2.54,-8.6h22.87c17.5,0 27.66,-13.34 27.66,-30.34V234.76C355.43,252.19 305.32,262.82 251.43,262.82z")
            .toNodes()
}

@Composable
fun AndroidRoboto() {

    var clicked by remember { mutableStateOf(false) }
    val headState by animateFloatAsState(
        targetValue = if (clicked) 0f else -200f,
        animationSpec = tween(durationMillis = 800, easing = EaseInOut),
        label = "rotation"
    )

    val bodyState by animateFloatAsState(
        targetValue = if (clicked) 0f else 400f,
        animationSpec = tween(durationMillis = 800, easing = EaseInOut),
        label = "rotation"
    )

    val leftArmState by animateFloatAsState(
        targetValue = if (clicked) 0f else -100f,
        animationSpec = tween(durationMillis = 800, easing = EaseInOut),
        label = "rotation"
    )

    val rightArmState by animateFloatAsState(
        targetValue = if (clicked) 0f else 100f,
        animationSpec = tween(durationMillis = 800, easing = EaseInOut),
        label = "rotation"
    )

//    val sizeState by animateDpAsState(targetValue = if (clicked) 200.dp else 0.dp ,
//        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
//        label = "size"
//    )

    val vectorPainter = rememberVectorPainter(
        defaultWidth = 200.dp,
        defaultHeight = 200.dp,
        viewportWidth = 502.86f,
        viewportHeight = 502.86f,
        autoMirror = true,
    ) { _, _ ->

        Group(name = "Robot-Head", translationY = headState) {
            Path(pathData = AndroidRobot.head, fill = SolidColor(Color(0xff57C927)))
            Path(pathData = AndroidRobot.headColor, fill = SolidColor(Color(0xff1CB71C)))
        }
        Group(name = "Robo", translationY = bodyState) {
            Path(pathData = body, fill = SolidColor(Color(0xff57C927)))
            Path(pathData = AndroidRobot.dress, fill = SolidColor(Color(0xff1CB71C)))
            Path(pathData = AndroidRobot.bodyDress, fill = SolidColor(Color(0xff049E42)))
        }
        Group(name = "Lef-Arms", translationX = leftArmState) {
            Path(pathData = AndroidRobot.leftArm, fill = SolidColor(Color(0xff57C927)))
            Path(pathData = AndroidRobot.leftArmCover, fill = SolidColor(Color(0xff049E42)))
        }
        Group(name = "Right-Arms", translationX = rightArmState) {
            Path(pathData = AndroidRobot.rightArm, fill = SolidColor(Color(0xff57C927)))
            Path(pathData = AndroidRobot.rightArmCover, fill = SolidColor(Color(0xff049E42)))
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            clicked = !clicked
        }
        .background(dayGradient), contentAlignment = Alignment.Center) {
        Image(
            painter = vectorPainter,
            contentDescription = "Android",
            modifier = Modifier.size(200.dp)
        )
    }

}
