package com.example.plantcare.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.plantcare.data.local.model.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Insert
    suspend fun insertSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM schedule")
    fun getAllSchedule() : Flow<List<ScheduleEntity>>

}