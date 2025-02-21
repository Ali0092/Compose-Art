package com.example.compose_art.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Drawing state to represent the current state (containing all the required information) in the current action
data class DrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList()
)
//data class
data class PathData(
    val id: String,
    val color: Color,
    val path: List<Offset>
)

sealed class DrawingAction { //Again a state management approach to handle user actions
    data object OnNewPathStart : DrawingAction() //singleton data class in other words a class with no constructor
    data class onDraw(val offset: Offset) : DrawingAction() //normal data class
    data object OnNewPathEnd : DrawingAction()
//    data class OnSelectColor(val color: Color) : DrawingAction()
    data object OnClearedCanvasClicked : DrawingAction()
}

val allColors = listOf(
    Color.Black,
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
)

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

    private fun onClearedCanvasClicked() {
        _drawingState.update {
            it.copy(
                currentPath = null,
                paths = emptyList()
            )
        }
    }

    private fun onNewPathStart() {
        _drawingState.update {
            it.copy(
                currentPath = PathData(
                    id = System.currentTimeMillis().toString(),
                    color = it.selectedColor,
                    path = emptyList()
                )
            )
        }
    }

    private fun onDraw(offset: Offset) {
        val currentPathData = drawingState.value.currentPath?: return
        _drawingState.update {
            it.copy(currentPath = currentPathData.copy(path = currentPathData.path + offset))
        }
    }

    private fun onNewPathEnd() {
        val currentPathData = drawingState.value.currentPath?: return
        _drawingState.update {
            it.copy(currentPath = null, paths = it.paths + currentPathData)
        }
    }

}