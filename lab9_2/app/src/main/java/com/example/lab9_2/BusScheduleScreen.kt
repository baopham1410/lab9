package com.example.lab9_2

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun BusScheduleScreen(context: Context) {
    val database = remember { BusScheduleDatabase.getDatabase(context).busScheduleDao() }
    var schedules by remember { mutableStateOf(listOf<BusSchedule>()) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            schedules = database.getAllSchedules()
        }
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(schedules) { schedule ->
            Text(text = "${schedule.stopName} - ${schedule.arrivalTime}", modifier = Modifier.padding(8.dp))
        }
    }
}
