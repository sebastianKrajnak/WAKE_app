package com.example.wake_app.data

import com.example.wake_app.R
import com.example.wake_app.model.GameButton
import com.example.wake_app.model.WeekdayButton

object DataSource {

    /*val alarms = listOf(
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1, true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2, false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3, true),
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1, true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2, false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3, true),
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1, true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2, false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3, true),
        Alarm(R.string.alarm_time_1, R.string.alarm_description_1, true),
        Alarm(R.string.alarm_time_2, R.string.alarm_description_2, false),
        Alarm(R.string.alarm_time_3, R.string.alarm_description_3, false),
    )*/

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

    val gameButtons = listOf(
        GameButton(0, R.string.game_connect, R.drawable.sequence),
        GameButton(1, R.string.game_equation, R.drawable.equation),
        GameButton(2, R.string.game_memory, R.drawable.brain),
        GameButton(3, R.string.game_sorting, R.drawable.hilo),
        GameButton(4, R.string.game_typo, R.drawable.typo)
    )

    val weekdayButtons = listOf(
        WeekdayButton(0, "M"),
        WeekdayButton(1, "T"),
        WeekdayButton(2, "W"),
        WeekdayButton(3, "T"),
        WeekdayButton(4, "F"),
        WeekdayButton(5, "S"),
        WeekdayButton(6, "S")
    )
}

//import android.app.PendingIntent.getActivity
//import android.database.Cursor
//import android.media.RingtoneManager
//import com.example.wake_app.R
//import com.example.wake_app.model.GameButton
/*

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
        )
    }
}*/