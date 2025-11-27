package com.example.plantcare.presentation.ai.comps

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                this.controller = controller
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        },
        modifier = modifier
    )
}
