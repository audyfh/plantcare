package com.example.plantcare.data.local.mapper

import com.example.plantcare.data.local.model.ScheduleEntity
import com.example.plantcare.domain.model.Schedule

fun ScheduleEntity.toDomain() : Schedule {
    return Schedule(
        id = id,
        title = title,
        description = description,
        date = date
    )
}

fun Schedule.toEntity() : ScheduleEntity {
    return ScheduleEntity(
        id = id,
        title = title,
        description = description,
        date = date
    )
}