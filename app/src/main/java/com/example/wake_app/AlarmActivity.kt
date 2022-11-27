package com.example.wake_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.wake_app.model.Game
import com.example.wake_app.screens.GameScreen


class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        System.out.println("Show lock screen here")
        setContent {
            MessageCard()
        }
    }
}

@Composable
fun MessageCard() {
    GameScreen (
        generateRandomGame()
    )
}


fun generateRandomGame() : Game {
    // TODO generate random game
    // get alarm// pass alarm to get games categories


    return Game("1", "5 + 5 / 5", "6", "Solve the equation")
}

