package com.cod3f1re.examdaystore.model.entities

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Locations::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationsDao(): LocationsDao
}