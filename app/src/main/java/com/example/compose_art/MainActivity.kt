package com.example.compose_art

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_art.composables.DrawingCanvas
import com.example.compose_art.ui.theme.ComposeArtTheme
import com.example.compose_art.viewmodel.DrawingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArtTheme {
                DrawingCanvas()
            }
        }
    }
}

