package com.example.compose_art

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import com.example.compose_art.composables.DrawingCanvas
import com.example.compose_art.ui.theme.ComposeArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArtTheme {
                DrawResizedImageOnCanvas()
            }
        }
    }
}

@Composable
fun DrawResizedImageOnCanvas() {

    var dragPath by remember { mutableStateOf<List<Offset>>(emptyList()) }

    val context = LocalContext.current
    val bitmap = remember {
        val resId = R.drawable.b6// Replace with your image
        val original = BitmapFactory.decodeResource(context.resources, resId)

        // Resize the bitmap to 50x50 pixels
        Bitmap.createScaledBitmap(original, 100, 100, true).asImageBitmap()
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFDFE5E3))
        .pointerInput(true) {
            detectDragGestures(onDragStart = { offset ->
                dragPath = listOf(offset)
            }, onDrag = { change, _ ->
                dragPath = dragPath + change.position
            })
        }) {

        for (drags in dragPath) {
            drawImage(bitmap, topLeft = drags)
        }


    }
}