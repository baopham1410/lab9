package com.example.lab9_2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM bus_schedule ORDER BY arrivalTime ASC")
    fun getAllSchedules(): List<BusSchedule>

    @Query("SELECT * FROM bus_schedule WHERE stopName = :stop ORDER BY arrivalTime ASC")
    fun getScheduleByStop(stop: String): List<BusSchedule>

    @Insert
    fun insertSchedule(schedule: BusSchedule)
}
