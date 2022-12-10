package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.os.*
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.wake_app.CHANNEL_ID
import com.example.wake_app.NOTIFICATION_CHANNEL_ID
import com.example.wake_app.model.minigames.Expression
import com.example.wake_app.screens.minigames.EquationMiniGameScreen
import com.example.wake_app.ui.theme.WAKE_appTheme


class EquationMiniGameActivity : SoundAndVibration() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cancel notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_CHANNEL_ID)

        setContent {
            WAKE_appTheme {
                EquationMiniGameScreen(Expression(1,10))
            }
        }

    }


}

