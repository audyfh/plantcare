package com.example.plantcare.util.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationRepository(
    private val context: Context
) {

    private val fusedClient =
        LocationServices.getFusedLocationProviderClient(context)

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): LocationResult? =
        suspendCancellableCoroutine { cont ->
            fusedClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        cont.resume(
                            LocationResult(
                                latitude = location.latitude,
                                longitude = location.longitude
                            ),
                            onCancellation = null
                        )
                    } else {
                        cont.resume(null, onCancellation = null)
                    }
                }
                .addOnFailureListener {
                    cont.resume(null, onCancellation = null)
                }
        }
}