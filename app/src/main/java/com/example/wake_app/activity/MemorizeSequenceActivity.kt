package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.NOTIFICATION_CHANNEL_ID
import com.example.wake_app.model.minigames.MemorizeSequence
import com.example.wake_app.screens.minigames.MemorizeSequenceScreen
import com.example.wake_app.ui.theme.WAKE_appTheme


class MemorizeSequenceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cancel notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_CHANNEL_ID)

        setContent {
            WAKE_appTheme {
                MemorizeSequenceScreen( MemorizeSequence(5, 300) )
            }
        }

    }
}

