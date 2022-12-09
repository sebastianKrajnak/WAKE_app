package com.example.wake_app.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.wake_app.model.minigames.ConstructWord
import com.example.wake_app.screens.minigames.ConstructWordMiniGameScreen



class ConstructWordGameActivity : ComponentActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent) // never called
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerateRandomGame()
        }
    }

}

@Composable
private fun GenerateRandomGame() {
    return ConstructWordMiniGameScreen (
        ConstructWord()
    )
}

