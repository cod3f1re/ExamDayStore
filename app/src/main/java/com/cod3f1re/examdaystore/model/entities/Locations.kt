package com.cod3f1re.examdaystore.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Locations.TABLE_NAME)
data class Locations(
    @ColumnInfo(name = "latitude") var latitude: Double?,
    @ColumnInfo(name = "longitude") var longitude: Double?
) {

    companion object {
        const val TABLE_NAME = "Locations"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "location_id")
    var locationId: Int = 0
}
