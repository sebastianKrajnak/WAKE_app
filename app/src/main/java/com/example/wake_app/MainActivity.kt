package com.example.wake_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import com.example.wake_app.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.core.content.ContextCompat
import com.example.wake_app.ui.theme.WAKE_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            WAKE_appTheme {
                // A surface container using the 'background' color from the theme
                //this.window.statusBarColor = ContextCompat.getColor(this, R.color.background_light)
                //this.window.navigationBarColor = ContextCompat.getColor(this, R.color.main_accent)
                /*val window: Window = this.window
                window.statusBarColor = if (isSystemInDarkTheme())
                    md_theme_dark_background.toArgb()
                else
                    md_theme_light_background.toArgb()

                window.navigationBarColor = if (isSystemInDarkTheme())
                    md_theme_dark_primary.toArgb()
                else
                    md_theme_light_primary.toArgb()*/

                MainScreen()
//                GameScreenPreview()
//                AlarmCreationPreview()
            }
        }
    }
}
