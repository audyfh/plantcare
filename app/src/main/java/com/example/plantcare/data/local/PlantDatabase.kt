package com.example.plantcare.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantcare.data.local.dao.MyPlantDao
import com.example.plantcare.data.local.dao.PlantDiagnosisDao
import com.example.plantcare.data.local.dao.ScheduleDao
import com.example.plantcare.data.local.model.MyPlantEntity
import com.example.plantcare.data.local.model.PlantDiagnosisEntity
import com.example.plantcare.data.local.model.ScheduleEntity

@Database(
    entities = [
        MyPlantEntity::class,
        PlantDiagnosisEntity::class,
        ScheduleEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class PlantDatabase : RoomDatabase() {

    abstract val myPlantDao: MyPlantDao
    abstract val plantDiagnosisDao: PlantDiagnosisDao
    abstract val scheduleDao: ScheduleDao


}