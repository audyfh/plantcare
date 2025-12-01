package com.example.plantcare.presentation.ai.diagnose

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.plantcare.presentation.ai.comps.CameraPreview
import com.example.plantcare.presentation.ai.diagnose.comp.DiagnosisResultCard
import com.example.plantcare.presentation.ai.diagnose.comp.SelectedPlantInfo
import com.example.plantcare.presentation.ai.identify.PlantIdentifyResultScreen
import com.example.plantcare.presentation.ai.identify.takePictureWithController
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SoftMint
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DiagnoseResultScreen(
    modifier: Modifier = Modifier,
    viewModel: DiagnosisViewModel,
    navigateBack: () -> Unit
) {

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
            viewModel.diagnosePlant(context, uri)
            viewModel.updateImageUri(uri)
        }
    }

    val selectedPlant = state.selectedPlant

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        // TOP BAR
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
                text = "Diagnose Plant",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryGreen
            )
            Spacer(Modifier.width(24.dp))
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Select or take picture from camera to diagnose the plant.",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark
        )
        Spacer(Modifier.height(16.dp))

        // 🔹 INFO TANAMAN YANG DIPILIH (dari MyPlant / Room)
        if (selectedPlant != null) {
            SelectedPlantInfo(plant = selectedPlant)
            Spacer(Modifier.height(16.dp))
        } else {
            Text(
                text = "No plant selected",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
            Spacer(Modifier.height(16.dp))
        }

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
                            text = "Camera permission is required to scan plant.",
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
                            Text("Grant Camera Permission")
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
                                viewModel.diagnosePlant(context, uri)
                                viewModel.updateImageUri(uri)
                            },
                            onError = { error ->
                                Log.e("DiagnosePlant", "Capture error: $error")
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
                    text = "Scan Camera",
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
                    text = "From Gallery",
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
        } else if (state.plantDiagnosis != null) {
            DiagnosisResultCard(diagnosis = state.plantDiagnosis!!)
        } else if (!state.error.isNullOrBlank()) {
            Text(
                text = state.error ?: "There is some error",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                val plant = state.selectedPlant
                val diag = state.plantDiagnosis
                if (plant != null && diag != null) {
                    val updatedPlant = plant.copy(
                        healthStatus = diag.health_status,
                        diagnosisResult = diag.diagnosis_result,
                        lastDiagnosisAt = System.currentTimeMillis()
                    )
                    viewModel.updatePlant(updatedPlant)
                    Toast.makeText(context, "Diagnose saved", Toast.LENGTH_SHORT).show()
                    navigateBack()
                }
            },
            enabled = selectedPlant != null && state.plantDiagnosis != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                disabledContainerColor = PrimaryGreen.copy(alpha = 0.4f),
                contentColor = OffWhite
            )
        ) {
            Text(
                text = when {
                    selectedPlant == null -> "No Plant Selected"
                    state.plantDiagnosis == null -> "Scan first"
                    else -> "Save Diagnose"
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
