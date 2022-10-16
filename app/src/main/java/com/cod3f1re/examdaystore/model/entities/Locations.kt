package com.cod3f1re.examdaystore.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Locations.TABLE_NAME)
data class Locations(
    @ColumnInfo(name = "latitude") val latitude: String?,
    @ColumnInfo(name = "longitude") val longitude: String?
) {
    companion object {
        const val TABLE_NAME = "Locations"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "location_id")
    var locationId: Int = 0
}
