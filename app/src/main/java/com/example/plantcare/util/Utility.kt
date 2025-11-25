package com.example.plantcare.util

import android.content.Context
import com.google.android.gms.location.LocationServices
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
}