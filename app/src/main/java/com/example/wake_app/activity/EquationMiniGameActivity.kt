package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.NOTIFICATION_CHANNEL_ID
import com.example.wake_app.model.minigames.Expression
import com.example.wake_app.screens.minigames.EquationMiniGameScreen


class EquationMiniGameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cancel notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_CHANNEL_ID)

        setContent {
            EquationMiniGameScreen(Expression(1,10))
        }
    }
}

