package com.cod3f1re.examdaystore.model.entities

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
@Database(entities = [Locations::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationsDao(): LocationsDao
}