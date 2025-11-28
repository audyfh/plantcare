package com.example.plantcare.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val description: String,
    val date: Long
)
