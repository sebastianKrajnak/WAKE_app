package com.example.wake_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import com.example.wake_app.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.model.minigames.ConstructWord
import com.example.wake_app.screens.minigames.ClickSequenceMiniGameScreen
import com.example.wake_app.screens.minigames.ConstructWordMiniGameScreen
import com.example.wake_app.ui.theme.WAKE_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            WAKE_appTheme {
                MainScreen()
                //ConstructWordMiniGameScreen(cw = ConstructWord())
                //ClickSequenceMiniGameScreen()
            }
        }
    }
}
