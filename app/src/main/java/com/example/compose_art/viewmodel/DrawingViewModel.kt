package com.example.compose_art.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.compose_art.model.PathData
import com.example.compose_art.states.DrawingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DrawingViewModel : ViewModel() {

    private val _drawingState = MutableStateFlow(DrawingState())
    val drawingState = _drawingState.asStateFlow()

    fun onClearedCanvasClicked() {
        _drawingState.update {
            it.copy(
                currentPath = null, paths = emptyList()
            )
        }
    }

    fun onNewPathStart() {
        _drawingState.update {
            it.copy(
                currentPath = PathData(
                    path = emptyList(), color = it.selectedColor, brush = it.selectedBrush
                )
            )
        }
    }

    fun onDraw(offset: Offset) {
        val currentPathData = drawingState.value.currentPath ?: return
        _drawingState.update {
            it.copy(
                currentPath = currentPathData.copy(
                    path = currentPathData.path + offset, color = currentPathData.color, brush = currentPathData.brush
                )
            )
        }
    }

    fun onNewPathEnd() {
        val currentPathData = drawingState.value.currentPath ?: return
        _drawingState.update {
            it.copy(currentPath = null, paths = it.paths + currentPathData)
        }
    }

    fun onSetColor(color: Color) {
        _drawingState.update {
            it.copy(selectedColor = color)
        }
    }

    fun onSetBrush(brush: ImageBitmap?) {
        _drawingState.update {
            it.copy(selectedBrush = brush)
        }
    }

}
