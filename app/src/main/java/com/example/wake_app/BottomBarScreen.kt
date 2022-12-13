package com.example.wake_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val tittle: String,
    val icon: ImageVector
) {
    // Bottom bar screens
    object Home: BottomBarScreen (
        route = "home",
        tittle = "Alarms",
        icon = Icons.Default.Home
    )

    object Statistics: BottomBarScreen (
        route = "statistics",
        tittle = "Statistics",
        icon = Icons.Default.BarChart
    )

    object Settings: BottomBarScreen (
        route = "settings",
        tittle = "Settings",
        icon = Icons.Default.Settings
    )

    // Pop-up screens
    object AlarmCreation: BottomBarScreen (
        route = "alarmcreation",
        tittle = "Alarm Creation",
        icon = Icons.Default.Info
    )

    object AlarmEdit: BottomBarScreen (
        route = "alarmedit",
        tittle = "Alarm Edit",
        icon = Icons.Default.Info
    )

}
