package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.NOTIFICATION_CHANNEL_ID
import com.example.wake_app.screens.minigames.ClickSequenceMiniGameScreen
import com.example.wake_app.ui.theme.WAKE_appTheme


class ClickSequenceGameActivity : SoundAndVibration() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cancel notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_CHANNEL_ID)

        setContent {
            WAKE_appTheme {
                ClickSequenceMiniGameScreen()
            }
        }

    }
}

