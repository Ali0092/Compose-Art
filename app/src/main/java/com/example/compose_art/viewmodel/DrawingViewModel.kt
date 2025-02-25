package com.example.compose_art.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Drawing state to represent the current state (containing all the required information) in the current action
data class DrawingState(
    val currentPath: PathData? = null,
    val selectedColor: Color = Color.Black,
    val paths: List<PathData> = emptyList()
)
//data class
data class PathData(
    val path: List<Offset>,
    val color: Color = Color.Black
)

sealed class DrawingAction { //Again a state management approach to handle user actions
    data object OnNewPathStart : DrawingAction() //singleton data class in other words a class with no constructor
    data class onDraw(val offset: Offset) : DrawingAction() //normal data class
    data object OnNewPathEnd : DrawingAction()
//    data class OnSelectColor(val color: Color) : DrawingAction()
    data object OnClearedCanvasClicked : DrawingAction()
}

class DrawingViewModel: ViewModel() {

      private val _drawingState = MutableStateFlow(DrawingState())
      val drawingState =_drawingState.asStateFlow()

      fun onAction(action: DrawingAction) {
          when(action){
              is DrawingAction.OnNewPathStart -> {
                  onNewPathStart()
              }
              is DrawingAction.onDraw -> {
                  onDraw(action.offset)
              }

              is DrawingAction.OnNewPathEnd -> {
                  onNewPathEnd()
              }
              is DrawingAction.OnClearedCanvasClicked -> {
                  onClearedCanvasClicked()
              }
          }
      }

    fun onClearedCanvasClicked() {
        _drawingState.update {
            it.copy(
                currentPath = null,
                paths = emptyList()
            )
        }
    }

    fun onNewPathStart() {
        _drawingState.update {
            it.copy(
                currentPath = PathData(
                    path = emptyList(),
                    color = it.selectedColor
                )
            )
        }
    }

    fun onDraw(offset: Offset) {
        val currentPathData = drawingState.value.currentPath?: return
        _drawingState.update {
            it.copy(currentPath = currentPathData.copy(path = currentPathData.path + offset,
                color = currentPathData.color))
        }
    }

    fun onNewPathEnd() {
        val currentPathData = drawingState.value.currentPath?: return
        _drawingState.update {
            it.copy(currentPath = null, paths = it.paths + currentPathData)
        }
    }

    fun onSetColor(color: Color) {
        _drawingState.update {
            it.copy(selectedColor = color)
        }
    }

}