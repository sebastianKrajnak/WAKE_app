package com.example.wake_app.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.wake_app.model.minigames.SortSequence
import com.example.wake_app.screens.minigames.SortSequenceMiGameScreen


class SequenceGameActivity : ComponentActivity() {

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
    return SortSequenceMiGameScreen (
        SortSequence(1,100,10)
    )
}

