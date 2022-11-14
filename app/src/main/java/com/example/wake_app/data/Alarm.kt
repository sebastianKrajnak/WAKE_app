package com.example.wake_app.data

import androidx.annotation.StringRes
import com.example.wake_app.R

data class Alarm(
    @StringRes val time: Int,
    @StringRes val description: Int,
    val active: Boolean = true
)

val alarms = listOf(
    Alarm(R.string.alarm_time_1, R.string.alarm_description_1,true),
    Alarm(R.string.alarm_time_2, R.string.alarm_description_2,false),
    Alarm(R.string.alarm_time_3, R.string.alarm_description_3,true),
)