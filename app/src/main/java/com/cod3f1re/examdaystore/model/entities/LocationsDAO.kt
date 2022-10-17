package com.cod3f1re.examdaystore.model.entities

import androidx.room.*

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
@Dao
interface LocationsDao {
    @Insert
    fun insert(location: Locations)

    @Update
    fun update(vararg location: Locations)

    @Delete
    fun delete(vararg location: Locations)

    @Query("SELECT * FROM " + Locations.TABLE_NAME)
    fun getLocations(): MutableList<Locations>
}