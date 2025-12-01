package com.example.plantcare.presentation.ai.identify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.camera.view.CameraController
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.plantcare.presentation.ai.comps.CameraPreview
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SoftMint
import com.google.accompanist.permissions.*
import org.koin.androidx.compose.koinViewModel
import java.io.File


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun IdentifyPlantScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val viewModel: IdentifyPlantViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }

    LaunchedEffect(Unit) {
        if (cameraPermissionState.status.isGranted) {
            cameraController.bindToLifecycle(lifecycleOwner)
        }
    }

    LaunchedEffect(cameraPermissionState.status.isGranted) {
        if (cameraPermissionState.status.isGranted) {
            cameraController.bindToLifecycle(lifecycleOwner)
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.identifyPlant(context, uri)
            viewModel.updateImageUri(uri)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = PrimaryGreen,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navigateBack() }
            )
            Text(
                text = "Plant Detail",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryGreen
            )
            Spacer(Modifier.width(24.dp))
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Arahkan kamera ke tanaman atau pilih foto dari galeri untuk mengenali jenis tanaman.",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark
        )
        Spacer(Modifier.height(16.dp))


        when {
            state.imageUri != null -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SoftMint)
                ) {
                    AsyncImage(
                        model = state.imageUri,
                        contentDescription = "Selected plant image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            !cameraPermissionState.status.isGranted -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(SoftMint),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Izin kamera diperlukan untuk scan tanaman.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayDark,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = { cameraPermissionState.launchPermissionRequest() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryGreen,
                                contentColor = OffWhite
                            )
                        ) {
                            Text("Izinkan Kamera")
                        }
                    }
                }
            }

            else -> {
                CameraPreview(
                    controller = cameraController,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
        Spacer(Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        takePictureWithController(
                            context = context,
                            controller = cameraController,
                            onImageCaptured = { uri ->
                                viewModel.identifyPlant(context, uri)
                                viewModel.updateImageUri(uri)
                            },
                            onError = { error ->
                                Log.e("IdentifyPlant", "Capture error: $error")
                            }
                        )
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = OffWhite
                )
            ) {
                Text(
                    text = "Scan Kamera",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            OutlinedButton(
                onClick = {
                    galleryLauncher.launch("image/*")
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, PrimaryGreen),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = PrimaryGreen
                )
            ) {
                Text(
                    text = "Dari Galeri",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(20.dp))


        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryGreen)
            }
        } else if (state.plant != null) {
            PlantIdentifyResultScreen(plant = state.plant!!)
        } else if (!state.error.isNullOrBlank()) {
            Text(
                text = state.error ?: "Terjadi kesalahan saat identifikasi.",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun takePictureWithController(
    context: Context,
    controller: LifecycleCameraController,
    onImageCaptured: (Uri) -> Unit,
    onError: (String) -> Unit
) {
    val outputDir = context.cacheDir
    val photoFile = File(
        outputDir,
        "plant-${System.currentTimeMillis()}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    val executor = ContextCompat.getMainExecutor(context)

    controller.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val uri = outputFileResults.savedUri ?: photoFile.toUri()
                onImageCaptured(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception.message ?: "Unknown error")
            }
        }
    )
}

