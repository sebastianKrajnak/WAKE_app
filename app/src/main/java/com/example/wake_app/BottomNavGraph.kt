package com.example.wake_app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wake_app.model.SharedViewModel
import com.example.wake_app.screens.*
import com.example.wake_app.screens.minigames.ClickSequenceMiniGameScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable( route = BottomBarScreen.Home.route) {
            HomeScreen(navController, sharedViewModel)
        }
        composable( route = BottomBarScreen.Statistics.route) {
            StatisticsScreen()
        }
        composable( route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController)
        }
        composable( route = BottomBarScreen.AlarmCreation.route) {
            AlarmCreationScreen(navController)
        }
        composable( route = BottomBarScreen.AlarmEdit.route) {
            AlarmEditScreen(navController, sharedViewModel)
        }
        composable( route = BottomBarScreen.SequenceGame.route) {
            ClickSequenceMiniGameScreen(navController)
        }
    }
}