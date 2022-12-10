package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.wake_app.model.minigames.SortSequence
import com.example.wake_app.screens.minigames.SortSequenceMiGameScreen


class SequenceGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cancel notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(0)

        setContent {
            GenerateRandomGame()
        }
    }

}

@Composable
private fun GenerateRandomGame() {
    return SortSequenceMiGameScreen (
        SortSequence(1,100,10)
    )
}

