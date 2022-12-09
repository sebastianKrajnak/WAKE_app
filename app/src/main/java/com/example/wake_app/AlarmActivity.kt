package com.example.wake_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.model.Alarm
import com.example.wake_app.model.Game
import com.example.wake_app.screens.GameScreen
import org.apache.commons.lang3.SerializationUtils


class AlarmActivity : ComponentActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent) // never called
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen (
                generateRandomGame()
            )
        }
    }

}



fun generateRandomGame() : Game {
    // TODO generate random game
    // get alarm// pass alarm to get games categories


    return Game("1", "5 + 5 / 5", "6", "Solve the equation")
}

