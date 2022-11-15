package com.example.wake_app.data

import com.example.wake_app.R

object DataSource {

    val alarms = listOf(
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1,true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2,false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3,true),
    )

    val languages = listOf(
        R.string.language_english,
        R.string.language_german,
        R.string.language_spanish,
        R.string.language_russian,
        R.string.language_mandarin
    )

    val alarmSounds = listOf(
        R.string.alarm_sound1,
        R.string.alarm_sound2,
        R.string.alarm_sound3,
        R.string.alarm_sound4,
        R.string.alarm_sound5,
    )

}