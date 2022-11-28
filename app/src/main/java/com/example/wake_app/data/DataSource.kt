package com.example.wake_app.data

import com.example.wake_app.R
import com.example.wake_app.model.Alarm
import com.example.wake_app.model.GameButton

object DataSource {

    val alarms = listOf(
        Alarm("5:06", "Wake up", true),
        Alarm("12:27", "Have lunch", false),
        Alarm("18:36", "Have dinner", true),
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

    val gameButtons = listOf(
        GameButton(R.string.game_connect, R.drawable.connect),
        GameButton(R.string.game_equation, R.drawable.equation),
        GameButton(R.string.game_memory, R.drawable.memory),
        GameButton(R.string.game_sorting, R.drawable.sorting),
        GameButton(R.string.game_typo, R.drawable.typo)
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