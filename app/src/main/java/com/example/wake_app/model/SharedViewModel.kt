package com.example.wake_app.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    var alarm by mutableStateOf<Alarm?>(null)
        private set

    fun addAlarmView(newAlarm: Alarm){
        alarm = newAlarm
    }
}