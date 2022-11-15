package com.example.wake_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wake_app.screens.AlarmCreationScreen
import com.example.wake_app.screens.HomeScreen
import com.example.wake_app.screens.SettingsScreen
import com.example.wake_app.screens.StatisticsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable( route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable( route = BottomBarScreen.Statistics.route) {
            StatisticsScreen()
        }
        composable( route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        composable( route = BottomBarScreen.AlarmCreation.route) {
            AlarmCreationScreen(navController)
        }
    }
}