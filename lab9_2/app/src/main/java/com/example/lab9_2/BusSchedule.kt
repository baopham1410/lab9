package com.example.lab9_2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus_schedule")
data class BusSchedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val stopName: String,
    val arrivalTime: String
)
