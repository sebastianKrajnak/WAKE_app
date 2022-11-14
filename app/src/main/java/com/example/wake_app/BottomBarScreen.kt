package com.example.wake_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val tittle: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen (
        route = "home",
        tittle = "Alarms",
        icon = Icons.Default.Home
    )

    object Statistics: BottomBarScreen (
        route = "statistics",
        tittle = "Statistics",
        icon = Icons.Default.Info
    )

    object Settings: BottomBarScreen (
        route = "settings",
        tittle = "Settings",
        icon = Icons.Default.Settings
    )
}
