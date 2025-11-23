package com.example.plantcare.util

import android.content.Context
import android.net.Uri

fun Context.readBytes(uri: Uri): ByteArray {
    return contentResolver.openInputStream(uri)?.use { inputStream ->
        inputStream.readBytes()
    } ?: throw IllegalArgumentException("Cannot open input stream for URI: $uri")
}
