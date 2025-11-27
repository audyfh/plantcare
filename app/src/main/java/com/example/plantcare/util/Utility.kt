package com.example.plantcare.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object Utility {

    fun formatDateTime(timestamp: Long): String {
        if (timestamp == 0L) return "-"
        val date = Date(timestamp)
        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return formatter.format(date)
    }

    fun isSameDay(timestamp: Long): Boolean {
        if (timestamp == 0L) return false

        val plantDate = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val today = LocalDate.now()

        return plantDate == today
    }

    fun uriToBitmap(
        context: Context,
        uri: Uri
    ): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun extractJson(text: String): String {
        val start = text.indexOf('{')
        val end = text.lastIndexOf('}')
        return if (start != -1 && end != -1 && end > start) {
            text.substring(start, end + 1)
        } else {
            text
        }
    }
}