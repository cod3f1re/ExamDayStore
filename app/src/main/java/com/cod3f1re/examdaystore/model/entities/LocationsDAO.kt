package com.cod3f1re.examdaystore.model.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Insert
    fun insert(location: Locations)

    @Update
    fun update(vararg location: Locations)

    @Delete
    fun delete(vararg location: Locations)

    @Query("SELECT * FROM " + Locations.TABLE_NAME)
    fun getLocations(): LiveData<List<Locations>>
}