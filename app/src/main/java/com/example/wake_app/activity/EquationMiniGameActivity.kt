package com.example.wake_app.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.model.minigames.Expression
import com.example.wake_app.screens.minigames.EquationMiniGameScreen


class EquationMiniGameActivity : ComponentActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent) // never called
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EquationMiniGameScreen(Expression(1,10))
        }
    }

}

