package com.example.wake_app.data

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