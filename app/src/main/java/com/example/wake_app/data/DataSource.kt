package com.example.wake_app.data

import com.example.wake_app.R

object DataSource {

    val alarms = listOf(
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1, true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2, false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3, true),
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

import android.app.PendingIntent.getActivity
import android.database.Cursor
import android.media.RingtoneManager
import com.example.wake_app.R
import com.example.wake_app.model.GameButton


class Datasource() {
    fun loadGameButtons(): List<GameButton> {
        return listOf<GameButton>(
            GameButton("Connect", R.drawable.connect),
            GameButton("Equation", R.drawable.equation),
            GameButton("Memory", R.drawable.memory),
            GameButton("Sorting", R.drawable.sorting),
            GameButton("Typo", R.drawable.typo))
    }

    fun getRingsTones(): List<String> {
        return listOf<String>(
            "Alarm_sound_1",
            "Alarm_sound_2",
            "Alarm_sound_3",
            "Alarm_sound_4",
            "Alarm_sound_5",
            "Alarm_sound_6",
        )
    }
}