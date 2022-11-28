package com.example.wake_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wake_app.model.Alarm
import com.example.wake_app.screens.AlarmCreationPreview
import com.example.wake_app.screens.GameScreenPreview

import com.example.wake_app.ui.theme.WAKE_appTheme
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WAKE_appTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
//                GameScreenPreview()
//                AlarmCreationPreview()
            }
        }
    }
}
